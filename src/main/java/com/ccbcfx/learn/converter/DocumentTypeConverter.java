package com.ccbcfx.learn.converter;

import com.ccbcfx.learn.enums.DocumentType;
import org.jooq.impl.EnumConverter;

/**
 * @Description:
 * @Author: 陆志庆
 * @CreateDate: 2019/3/1 11:06
 */
public class DocumentTypeConverter extends EnumConverter<Integer, DocumentType> {
    public DocumentTypeConverter(){
        super(Integer.class,DocumentType.class);
    }
}
