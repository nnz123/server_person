package com.ccbcfx.learn.converter;

import com.ccbcfx.learn.enums.StaffStatusType;
import org.jooq.impl.EnumConverter;

/**
 * @Description:
 * @Author: 陆志庆
 * @CreateDate: 2019/3/1 10:59
 */
public class StaffStatusTypeConverter extends EnumConverter {
    public StaffStatusTypeConverter(){
        super(Integer.class, StaffStatusType.class);
    }
}
