package com.ccbcfx.learn.controller;


import com.ccbcfx.learn.remote.dto.ConditionsDto;
import com.ccbcfx.learn.remote.dto.StaffDto;
import com.ccbcfx.learn.consumer.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping(path = "/staff")
    public int addStaff(@RequestBody StaffDto staff) {
        return staffService.createStaff(staff);
    }

    @DeleteMapping(path = "/staff/{id}")
    public boolean delete(@PathVariable(value = "id") int id, @RequestParam int deleteBy) {
        return staffService.delete(id, deleteBy);
    }

    @PutMapping(path = "/staff/{id}")
    StaffDto updateStaff(@PathVariable(value = "id") int id,
                         @RequestBody StaffDto staffDto) {
        return staffService.updateStaff(id, staffDto);
    }

    @RequestMapping(path = "/staff/profile/{id}", method = RequestMethod.PATCH)
    boolean updateStaffPortrait(@PathVariable(value = "id") int id, @RequestParam String imgUrl) {
        return staffService.updatePortrait(id, imgUrl);
    }

    @GetMapping(path = "/staff/{id}")
    public StaffDto getStaff(@PathVariable(value = "id") int id) {
        return staffService.findOne(id);
    }

    @PutMapping(path = "/staff/leave/{id}")
    boolean leave(@PathVariable(value = "id") int id,
                  @RequestParam(value = "name") String name,
                  @RequestParam(value = "leaveTime") Date leaveTime,
                  @RequestParam(value = "leaveReason") String leaveReason){
       return staffService.leave( id, name, leaveTime, leaveReason);
    }


    @PostMapping(path = "/staff/search")
    List<StaffDto> getStaffs(@RequestBody ConditionsDto conditionsDto,
                             @RequestParam int offset,
                             @RequestParam int size) {
        return staffService.findByConditions(conditionsDto, offset, size);
    }


}
