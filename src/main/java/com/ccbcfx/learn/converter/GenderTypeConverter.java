package com.ccbcfx.learn.converter;

import com.ccbcfx.learn.enums.GenderType;
import org.jooq.impl.EnumConverter;

/**
 * @Description:
 * @Author: 陆志庆
 * @CreateDate: 2019/3/1 11:05
 */
public class GenderTypeConverter extends EnumConverter<Integer,GenderType> {
    public GenderTypeConverter(){
        super(Integer.class,GenderType.class);
    }
}