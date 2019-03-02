package com.ccbcfx.learn.controller;


import com.ccbcfx.learn.remote.dto.ConditionsDto;
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

    @PostMapping(path = "/staff/search")
    List<StaffDto> getStaffs(@RequestBody ConditionsDto conditionsDto, @RequestParam int offset, @RequestParam int size){
        return staffService.findByConditions(conditionsDto,offset,size);
    }

    @GetMapping(path = "/staff/profile/{id}")
    String getProfilePath(@PathVariable int id){
        return staffService.findProfilePath(id);
    }

    @DeleteMapping(path = "/staff/{id}")
    public boolean delete(@PathVariable(value = "id") int id){
        return staffService.delete(id);
    }

    /**
     * 更新对象
     *
     * @param id
     * @param staffDto
     * @return
     */
    @PutMapping(path = "/Staff/{id}")
    StaffDto updateStaff(@PathVariable(value = "id") int id,
                         @RequestBody StaffDto staffDto){
        return staffService.updateStaff(id,staffDto);
    }

}
