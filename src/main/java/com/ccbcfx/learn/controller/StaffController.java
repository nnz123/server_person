package com.ccbcfx.learn.controller;


import com.ccbcfx.learn.service.StaffService;
import com.ccbcfx.learn.tables.pojos.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffController {
    @Autowired
    StaffService staffService;
    @PostMapping(path = "/staff")
    public int addStaff(@RequestBody Staff staff){
       return staffService.createStaff(staff);
    }
}
