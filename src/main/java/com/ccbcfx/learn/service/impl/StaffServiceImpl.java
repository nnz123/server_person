package com.ccbcfx.learn.service.impl;

import com.ccbcfx.learn.remote.dto.ConditionsDto;
import com.ccbcfx.learn.remote.dto.StaffDto;
import com.ccbcfx.learn.service.StaffService;
import com.ccbcfx.learn.util.StringUtil;

import ma.glasnost.orika.MapperFactory;
import org.jooq.Condition;
import org.jooq.TableField;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.ccbcfx.learn.tables.Staff.STAFF;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    MapperFactory mapperFactory;
    @Autowired
    StaffDao staffDao;

    @Override
    public int createStaff(StaffDto staffDto) {
        Staff staff = mapperFactory.getMapperFacade().map(staffDto, Staff.class);
        return staffDao.addStaff(staff);
    }

    @Override
    public List<StaffDto> findAll() {
        List<StaffDto> staffDtos = new ArrayList<>();
        List<Staff> staffs = staffDao.findAll();

        for (Staff staff : staffs) {
            StaffDto staffDto = mapperFactory.getMapperFacade().map(staff, StaffDto.class);
            staffDtos.add(staffDto);
        }
        return staffDtos;
    }

    @Override
    public List<StaffDto> findByConditions(ConditionsDto conditionsDto, int offset, int size) throws NoSuchFieldException, IllegalAccessException {
        List<Condition> conditions = new ArrayList<>();
        Field[] fields = ConditionsDto.class.getFields();
        for (Field field : fields) {
            if (null == field.get(conditionsDto)) {
                continue;
            }
            String fieldName = field.getName();
            fieldName = StringUtil.camelToUnderline(fieldName).toUpperCase();
            if (fieldName.contains("Begin")) {
                fieldName = fieldName.replaceAll("Begin", "At");
                Field field1 = com.ccbcfx.learn.tables.Staff.class.getField(fieldName);
                conditions.add(((TableField) field1.get(STAFF)).ge(field.get(conditionsDto)));
                continue;
            }
            if (fieldName.contains("End")) {
                fieldName = fieldName.replaceAll("End", "At");
                Field field2 = com.ccbcfx.learn.tables.Staff.class.getField(fieldName);
                conditions.add(((TableField) field2.get(STAFF)).le(field.get(conditionsDto)));
                continue;
            }
            Field field3 = com.ccbcfx.learn.tables.Staff.class.getField(fieldName);
            conditions.add(((TableField) field3.get(STAFF)).ge(field.get(conditionsDto)));
        }
        List<Staff> staffList = staffDao.findStaffByConditions(conditions.toArray(new Condition[conditions.size()]));
        List<StaffDto> staffDtoList = new ArrayList<>();
        staffDtoList = mapperFactory.getMapperFacade().mapAsList(staffList, StaffDto.class);

        return null;
    }


    @Override
    public StaffDto findOne(int id) {
        Staff staff = staffDao.findById(UInteger.valueOf(id));
        StaffDto staffDto = mapperFactory.getMapperFacade().map(staff, StaffDto.class);
        return staffDto;
    }

    @Override
    public String findProfilePath(int id) {
        return staffDao.findImgUrlById(UInteger.valueOf(id));
    }

    @Override
    public boolean delete(int id) {
        return staffDao.delete(id);
    }
}
