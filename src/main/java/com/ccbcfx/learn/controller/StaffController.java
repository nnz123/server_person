package com.ccbcfx.learn.controller;


import com.ccbcfx.learn.remote.dto.StaffDto;
import com.ccbcfx.learn.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping(path = "/staff")
    public int addStaff(@RequestBody StaffDto staff){
       return staffService.createStaff(staff);
    }

    @GetMapping(path = "/staff/list")
    public List<StaffDto> getStaffs(){
        return staffService.findAll();
    }

    @DeleteMapping(path = "/staff/{id}")
    public boolean delete(@PathVariable(value = "id") int id){
        return staffService.delete(id);
    }

}
