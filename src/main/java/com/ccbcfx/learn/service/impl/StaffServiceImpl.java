package com.ccbcfx.learn.service.impl;

import com.ccbcfx.learn.remote.dto.ConditionsDto;
import com.ccbcfx.learn.remote.dto.StaffDto;
import com.ccbcfx.learn.service.StaffService;
import com.ccbcfx.learn.tables.daos.StaffDao;
import com.ccbcfx.learn.tables.pojos.Staff;
import ma.glasnost.orika.MapperFactory;
import org.jooq.Condition;
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
        List<StaffDto> staffDtos=new ArrayList<>();
        List<Staff> staffs=staffDao.findAll();

        for(Staff staff:staffs){
            StaffDto staffDto = mapperFactory.getMapperFacade().map(staff, StaffDto.class);
            staffDtos.add(staffDto);
        }
        return staffDtos;
    }

    @Override
    public List<StaffDto> findByConditions(ConditionsDto conditionsDto, int offset, int size) {
        List<Condition> conditions=new ArrayList<>();
        Field[] fields=ConditionsDto.class.getFields();
        for(Field field:fields){
            String fieldName=field.getName();
            if(fieldName.contains("Begin")){
                fieldName=fieldName.replaceAll("Begin","At");
                conditions.add(STAFF.NAME.eq())
            }
            if(fieldName.contains("End")){
                fieldName=fieldName.replaceAll("End","At");
            }

        }

        return null;
    }

    public static void main(String[] args) {
        String name="createBegin";

        System.out.println(name.contains("Begin"));
    }

    @Override
    public StaffDto findOne(int id) {
        Staff staff=staffDao.findById(UInteger.valueOf(id));
        StaffDto staffDto=mapperFactory.getMapperFacade().map(staff, StaffDto.class);
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
