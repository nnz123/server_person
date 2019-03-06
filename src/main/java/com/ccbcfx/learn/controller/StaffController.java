package com.ccbcfx.learn.controller;


import com.ccbcfx.learn.remote.dto.ConditionsDTO;
import com.ccbcfx.learn.remote.dto.PageStaffDTO;
import com.ccbcfx.learn.remote.dto.StaffDTO;
import com.ccbcfx.learn.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Description:
 * @Author: 陆志庆
 * @CreateDate: 2019/2/27 14:02
 */
@RestController
public class StaffController {
    @Autowired
    private StaffService staffService;

    /**
     * 增加员工
     *
     * @param staff
     * @return
     */
    @RequestMapping(path = "/staff",
            method = RequestMethod.POST)
    public int
    addStaff(@RequestBody StaffDTO staff) {
        return staffService.createStaff(staff);
    }

    /**
     * 删除员工
     *
     * @param id
     * @param deleteBy
     * @return
     */
    @RequestMapping(path = "/staff/{id}",
            method = RequestMethod.DELETE)
    public boolean
    delete(@PathVariable int id,
           @RequestParam int deleteBy) {
        return staffService.delete(id, deleteBy);
    }


    /**
     * 修改员工
     *
     * @param id
     * @param staffDto
     * @return
     */
    @RequestMapping(path = "/staff/{id}",
            method = RequestMethod.PUT)
    public boolean
    updateStaff(@PathVariable int id,
                @RequestBody StaffDTO staffDto) {
        return staffService.updateStaff(id, staffDto);
    }

    /**
     * 修改头像url
     *
     * @param id
     * @param imgUrl
     * @return
     */
    @RequestMapping(path = "/staff/profile/{id}",
            method = RequestMethod.PUT)
    public boolean
    updateStaffPortrait(@PathVariable int id,
                        @RequestParam String imgUrl) {
        return staffService.updatePortrait(id, imgUrl);
    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/staff/{id}",
            method = RequestMethod.GET)
    public StaffDTO
    getStaff(@PathVariable int id) {
        return staffService.findOne(id);
    }

    /**
     * 员工离职
     *
     * @param id
     * @param name
     * @param leaveTime
     * @param leaveReason
     * @return
     */
    @RequestMapping(path = "/staff/leave/{id}",
            method = RequestMethod.PUT)
    public boolean
    leave(@PathVariable int id,
          @RequestParam(value = "name") String name,
          @RequestParam(value = "leaveTime") Date leaveTime,
          @RequestParam(value = "leaveReason") String leaveReason) {
        return staffService.leave(id, name, leaveTime, leaveReason);
    }


    /**
     * 根据条件查询员工数据
     *
     * @param conditionsDto
     * @param offset
     * @param size
     * @return
     */
    @RequestMapping(path = "/staff/search",
            method = RequestMethod.POST)
    public PageStaffDTO
    getStaffs(@RequestBody ConditionsDTO conditionsDto,
              @RequestParam int offset,
              @RequestParam int size) {
        return staffService.findByConditions(offset, size, conditionsDto);
    }

    /**
     * 批量查询员工
     *
     * @param offset
     * @param size
     * @return
     */
    @RequestMapping(path = "/staff/list",
            method = RequestMethod.GET)
    public PageStaffDTO
    getStaffList(@RequestParam(value = "offset") int offset,
                 @RequestParam(value = "size") int size) {
        return staffService.getStaffList(offset, size);
    }


}
