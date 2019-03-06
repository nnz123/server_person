package com.ccbcfx.learn.service.impl;

import com.ccbcfx.learn.enums.StaffStatusType;
import com.ccbcfx.learn.message.StaffLeaveMessage;
import com.ccbcfx.learn.mq.PersonSender;
import com.ccbcfx.learn.remote.dto.ConditionsDTO;
import com.ccbcfx.learn.remote.dto.PageStaffDTO;
import com.ccbcfx.learn.remote.dto.StaffDTO;
import com.ccbcfx.learn.service.StaffService;
import com.ccbcfx.learn.tables.daos.StaffDao;
import com.ccbcfx.learn.tables.pojos.Staff;

import com.ccbcfx.learn.tables.records.StaffRecord;
import com.ccbcfx.learn.util.BeanUtil;
import com.ccbcfx.learn.util.StringUtil;
import org.jooq.Condition;
import org.jooq.TableField;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

import static com.ccbcfx.learn.tables.Staff.STAFF;

/**
 * @Description:
 * @Author: 陆志庆
 * @CreateDate: 2019/3/1 14:02
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private PersonSender personSender;

    @Override
    public int createStaff(StaffDTO staffDto) {
        Staff staff = BeanUtil.map(staffDto, Staff.class);
        return staffDao.addStaff(staff);
    }

    @Override
    public boolean delete(int id, int deleteBy) {
        return staffDao.delete(UInteger.valueOf(id), deleteBy);
    }

    @Override
    public boolean leave(int id, String name, Date leaveTime, String leaveReason) {
        boolean result = staffDao.updateStatus(UInteger.valueOf(id), StaffStatusType.leave);
        personSender.send(new StaffLeaveMessage(id, name, new Date(), leaveReason));
        return result;
    }

    @Override
    public boolean updateStaff(int id, StaffDTO staffDto) {
        Map<TableField<StaffRecord, ?>, Object> params = new HashMap<>(8);
        if (null != staffDto.getBirthday()) {
            params.put(STAFF.BIRTHDAY,
                    staffDto.getBirthday());
        }
        if (null != staffDto.getDocumentType()) {
            params.put(STAFF.DOCUMENT_TYPE,
                    staffDto.getDocumentType());
        }
        if (null != staffDto.getGender()) {
            params.put(STAFF.GENDER,
                    staffDto.getGender());
        }
        if (null != staffDto.getName()
                && !"".equals(staffDto.getName())) {
            params.put(STAFF.NAME,
                    staffDto.getName());
        }
        if (null != staffDto.getDocumentNumber()
                && !"".equals(staffDto.getDocumentNumber())) {
            params.put(STAFF.DOCUMENT_NUMBER,
                    staffDto.getDocumentNumber());
        }
        if (null != staffDto.getPhone()
                && !"".equals(staffDto.getPhone())) {
            params.put(STAFF.PHONE,
                    staffDto.getPhone());
        }
        params.put(STAFF.UPDATE_BY, staffDto.getUpdateBy());
        params.put(STAFF.UPDATE_AT, staffDto.getUpdateAt());
        return staffDao.update(UInteger.valueOf(id),params);
    }


    @Override
    public boolean updatePortrait(int id, String imgUrl) {
        return staffDao.updateImgUrl(UInteger.valueOf(id), imgUrl);
    }

    @Override
    public StaffDTO findOne(int id) {
        Staff staff = staffDao.findById(UInteger.valueOf(id));
        StaffDTO staffDto = BeanUtil.map(staff, StaffDTO.class);
        return staffDto;
    }


    @Override
    public PageStaffDTO findByConditions(int offset,
                                         int size,
                                         ConditionsDTO conditionsDto) {
        List<Condition> conditions = new ArrayList<>();
        try {
            conditions = createConditions(conditionsDto);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        List<Staff> staffList = staffDao.findStaffByConditions(offset, size, conditions.toArray(new Condition[conditions.size()]));
        List<StaffDTO> staffDtoList = BeanUtil.mapAsList(staffList, StaffDTO.class);
        int count = staffDao.count(conditions);
        return new PageStaffDTO(count, staffDtoList);
    }

    @Override
    public PageStaffDTO getStaffList(int offset, int size) {
        List<Staff> staffList = staffDao.findAll(offset, size);
        List<StaffDTO> staffDtoList = BeanUtil.mapAsList(staffList, StaffDTO.class);
        int count = staffDao.count(Arrays.asList(STAFF.ENABLED.eq((byte) 1)));
        return new PageStaffDTO(count,staffDtoList);
    }

    /**
     * 构造查询条件
     *
     * @param conditionsDto 查询条件封装体
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private List<Condition> createConditions(ConditionsDTO conditionsDto) throws IllegalAccessException, NoSuchFieldException {
        List<Condition> conditions = new ArrayList<>();
        Field[] fields = ConditionsDTO.class.getDeclaredFields();
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
            Field staffField = com.ccbcfx.learn.tables.Staff.class.getField(fieldName);
            conditions.add(((TableField) staffField.get(STAFF)).eq(field.get(conditionsDto)));
        }
        return conditions;
    }


}
