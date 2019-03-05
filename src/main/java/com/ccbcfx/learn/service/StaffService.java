package com.ccbcfx.learn.service;


import com.ccbcfx.learn.remote.dto.ConditionsDTO;
import com.ccbcfx.learn.remote.dto.PageStaffDTO;
import com.ccbcfx.learn.remote.dto.StaffDTO;

import java.util.Date;


public interface StaffService {

    /**
     * 增加员工
     *
     * @param staff
     * @return
     */
    int createStaff(StaffDTO staff);

    /**
     * 通过id查询员工
     *
     * @param id
     * @return
     */
    StaffDTO findOne(int id);


    /**
     * 通过条件查询员工
     *
     * @param conditionsDto
     * @param offset
     * @param size
     * @return
     */
    PageStaffDTO findByConditions(ConditionsDTO conditionsDto, int offset, int size);

    /**
     * 删除员工
     *
     * @param id
     * @param deleteBy
     * @return
     */
    boolean delete(int id, int deleteBy);

    /**
     * 员工离职
     *
     * @param id
     * @param name
     * @param leaveTime
     * @param leaveReason
     * @return
     */
    boolean leave(int id, String name, Date leaveTime, String leaveReason);

    /**
     * 更新员工
     *
     * @param id
     * @param staffDto
     * @return
     */
    boolean updateStaff(int id, StaffDTO staffDto);

    /**
     * 上传员工头像
     *
     * @param id
     * @param imgUrl
     * @return
     */
    boolean updatePortrait(int id, String imgUrl);

    /**
     * 批量查询员工
     *
     * @param offset
     * @param size
     * @return
     */
    PageStaffDTO getStaffList(int offset, int size);


}