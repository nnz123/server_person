package com.ccbcfx.learn.consumer;


import com.ccbcfx.learn.remote.dto.ConditionsDto;
import com.ccbcfx.learn.remote.dto.StaffDto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public interface StaffService {

    /**
     * 增加员工
     *
     * @param staff
     * @return
     */
    int createStaff(StaffDto staff);

    /**
     * 通过id查询员工
     *
     * @param id
     * @return
     */
    StaffDto findOne(int id);

    /**
     * 查询员工
     *
     * @param offset
     * @param size
     * @return
     */
    List<StaffDto> findAll(int offset, int size);

    /**
     * 通过条件查询员工
     *
     * @param conditionsDto
     * @param offset
     * @param size
     * @return
     */
    List<StaffDto> findByConditions(ConditionsDto conditionsDto, int offset, int size);

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
    StaffDto updateStaff(int id, StaffDto staffDto);

    /**
     * 上传员工头像
     *
     * @param id
     * @param imgUrl
     * @return
     */
    boolean updatePortrait(int id, String imgUrl);

}