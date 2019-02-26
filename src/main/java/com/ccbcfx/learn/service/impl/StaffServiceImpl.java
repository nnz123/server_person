package com.ccbcfx.learn.service.impl;

import com.ccbcfx.learn.remote.dto.StaffDto;
import com.ccbcfx.learn.service.StaffService;
import com.ccbcfx.learn.tables.daos.StaffDao;
import com.ccbcfx.learn.tables.pojos.Staff;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public boolean delete(int id) {
        return staffDao.delete(id);
    }
}
