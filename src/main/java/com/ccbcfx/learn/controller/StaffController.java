package com.ccbcfx.learn.controller;


import com.ccbcfx.learn.remote.dto.ConditionsDTO;
import com.ccbcfx.learn.remote.dto.PageStaffDTO;
import com.ccbcfx.learn.remote.dto.StaffDTO;
import com.ccbcfx.learn.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping(path = "/staff")
    public int addStaff(@RequestBody StaffDTO staff) {
        return staffService.createStaff(staff);
    }

    @DeleteMapping(path = "/staff/{id}")
    public boolean delete(@PathVariable(value = "id") int id, @RequestParam int deleteBy) {
        return staffService.delete(id, deleteBy);
    }

    @PutMapping(path = "/staff/{id}")
    boolean updateStaff(@PathVariable(value = "id") int id,
                        @RequestBody StaffDTO staffDto) {
        return staffService.updateStaff(id, staffDto);
    }

    @RequestMapping(path = "/staff/profile/{id}", method = RequestMethod.PUT)
    boolean updateStaffPortrait(@PathVariable(value = "id") int id,
                                @RequestParam String imgUrl) {
        return staffService.updatePortrait(id, imgUrl);
    }

    @GetMapping(path = "/staff/{id}")
    public StaffDTO getStaff(@PathVariable(value = "id") int id) {
        return staffService.findOne(id);
    }

    @PutMapping(path = "/staff/leave/{id}")
    boolean leave(@PathVariable(value = "id") int id,
                  @RequestParam(value = "name") String name,
                  @RequestParam(value = "leaveTime") Date leaveTime,
                  @RequestParam(value = "leaveReason") String leaveReason) {
        return staffService.leave(id, name, leaveTime, leaveReason);
    }


    @PostMapping(path = "/staff/search")
    PageStaffDTO getStaffs(@RequestBody ConditionsDTO conditionsDto,
                             @RequestParam int offset,
                             @RequestParam int size) {
        return staffService.findByConditions(conditionsDto, offset, size);
    }
    @GetMapping(path = "/staff/list")
    PageStaffDTO getStaffList(@RequestParam(value = "offset") int offset,
                              @RequestParam(value = "size") int size){
        return staffService.getStaffList(offset,size);
    }



}
