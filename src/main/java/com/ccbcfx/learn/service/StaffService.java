package com.ccbcfx.learn.service;


import com.ccbcfx.learn.remote.dto.ConditionsDto;
import com.ccbcfx.learn.remote.dto.StaffDto;

import java.util.List;


public interface StaffService {

    /**
     *
     * @param staff
     * @return
     */
    int createStaff(StaffDto staff);

    StaffDto findOne(int id);
    String findProfilePath(int id);

    List<StaffDto> findAll();

    List<StaffDto> findByConditions(ConditionsDto conditionsDto,int offset,int size);

    boolean delete(int id);


}