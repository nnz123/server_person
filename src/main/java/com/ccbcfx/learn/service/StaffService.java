package com.ccbcfx.learn.service;


import com.ccbcfx.learn.remote.dto.StaffDto;

import java.util.List;


public interface StaffService {

    /**
     *
     * @param staff
     * @return
     */
    int createStaff(StaffDto staff);

    List<StaffDto> findAll();

    boolean delete(int id);


}