package com.ccbcfx.learn.consumer.impl;

import com.ccbcfx.learn.enums.StaffStatusType;
import com.ccbcfx.learn.message.StaffLeaveMessage;
import com.ccbcfx.learn.mq.PersonSender;
import com.ccbcfx.learn.remote.dto.ConditionsDto;
import com.ccbcfx.learn.remote.dto.StaffDto;
import com.ccbcfx.learn.consumer.StaffService;
import com.ccbcfx.learn.tables.daos.StaffDao;
import com.ccbcfx.learn.tables.pojos.Staff;

import com.ccbcfx.learn.util.StringUtil;
import ma.glasnost.orika.MapperFactory;
import org.jooq.Condition;
import org.jooq.TableField;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ccbcfx.learn.tables.Staff.STAFF;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    MapperFactory mapperFactory;
    @Autowired
    StaffDao staffDao;
    @Autowired
    PersonSender personSender;

    @Override
    public int createStaff(StaffDto staffDto) {
        Staff staff = mapperFactory.getMapperFacade().map(staffDto, Staff.class);
        return staffDao.addStaff(staff);
    }

    @Override
    public boolean delete(int id, int deleteBy) {
        return staffDao.delete(id, deleteBy);
    }

    @Override
    public boolean leave(int id, String name, Date leaveTime, String leaveReason) {
        boolean result = staffDao.updateStatus(UInteger.valueOf(id), StaffStatusType.leave);
        personSender.send(new StaffLeaveMessage(id,name,new Date(),leaveReason));
        return result;
    }

    @Override
    public StaffDto updateStaff(int id, StaffDto staffDto) {
        Staff staff = mapperFactory.getMapperFacade().map(staffDto, Staff.class);
        return mapperFactory.getMapperFacade().map(staffDao.updateWithReturn(staff, UInteger.valueOf(id)), StaffDto.class);
    }


    @Override
    public boolean updatePortrait(int id, String imgUrl) {
        return staffDao.updateImgUrl(id, imgUrl);
    }

    @Override
    public StaffDto findOne(int id) {
        Staff staff = staffDao.findById(UInteger.valueOf(id));
        StaffDto staffDto = mapperFactory.getMapperFacade().map(staff, StaffDto.class);
        return staffDto;
    }

    @Override
    public List<StaffDto> findAll(int offset, int size) {
        List<StaffDto> staffDtos = new ArrayList<>();
        List<Staff> staffs = staffDao.findAll(offset, size);
        for (Staff staff : staffs) {
            StaffDto staffDto = mapperFactory.getMapperFacade().map(staff, StaffDto.class);
            staffDtos.add(staffDto);
        }
        return staffDtos;
    }

    @Override
    public List<StaffDto> findByConditions(ConditionsDto conditionsDto, int offset, int size) {
        List<Condition> conditions = new ArrayList<>();
        try {
            conditions = createConditions(conditionsDto);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        List<Staff> staffList =
                staffDao.findStaffByConditions(
                        conditions.toArray(new Condition[conditions.size()]),
                        offset,
                        size);
        return mapperFactory.getMapperFacade().mapAsList(staffList, StaffDto.class);
    }

    /**
     * 构造查询条件
     *
     * @param conditionsDto 查询条件封装体
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private List<Condition> createConditions(ConditionsDto conditionsDto) throws IllegalAccessException, NoSuchFieldException {
        List<Condition> conditions = new ArrayList<>();
        Field[] fields = ConditionsDto.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (null == field.get(conditionsDto)) {
                continue;
            }
            String fieldName = field.getName();
            fieldName = StringUtil.camelToUnderline(fieldName).toUpperCase();
            if (fieldName.contains("_BEGIN")) {
                fieldName = fieldName.replaceAll("_BEGIN", "_AT");
                Field staffField = com.ccbcfx.learn.tables.Staff.class.getDeclaredField(fieldName);
                conditions.add(((TableField) staffField.get(STAFF)).ge(field.get(conditionsDto)));
                continue;
            }
            if (fieldName.contains("_END")) {
                fieldName = fieldName.replaceAll("_END", "_AT");
                Field staffField = com.ccbcfx.learn.tables.Staff.class.getField(fieldName);
                conditions.add(((TableField) staffField.get(STAFF)).le(field.get(conditionsDto)));
                continue;
            }
            Field field3 = com.ccbcfx.learn.tables.Staff.class.getField(fieldName);
            conditions.add(((TableField) field3.get(STAFF)).eq(field.get(conditionsDto)));
        }
        return conditions;
    }


}
