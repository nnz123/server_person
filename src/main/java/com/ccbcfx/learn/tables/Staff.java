/*
 * This file is generated by jOOQ.
*/
package com.ccbcfx.learn.tables;


import com.ccbcfx.learn.tables.records.StaffRecord;
import com.ccbcfx.learn.converter.DocumentTypeConverter;
import com.ccbcfx.learn.converter.GenderTypeConverter;
import com.ccbcfx.learn.converter.StaffStatusTypeConverter;
import com.ccbcfx.learn.enums.DocumentType;
import com.ccbcfx.learn.enums.GenderType;
import com.ccbcfx.learn.enums.StaffStatusType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Staff extends TableImpl<StaffRecord> {

    private static final long serialVersionUID = 179586393;

    /**
     * The reference instance of <code>learn.staff</code>
     */
    public static final Staff STAFF = new Staff();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StaffRecord> getRecordType() {
        return StaffRecord.class;
    }

    /**
     * The column <code>learn.staff.id</code>.
     */
    public final TableField<StaffRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>learn.staff.name</code>.
     */
    public final TableField<StaffRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>learn.staff.birthday</code>.
     */
    public final TableField<StaffRecord, LocalDate> BIRTHDAY = createField("birthday", org.jooq.impl.SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>learn.staff.phone</code>.
     */
    public final TableField<StaffRecord, String> PHONE = createField("phone", org.jooq.impl.SQLDataType.VARCHAR.length(16).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>learn.staff.gender</code>.
     */
    public final TableField<StaffRecord, GenderType> GENDER = createField("gender", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "", new GenderTypeConverter());

    /**
     * The column <code>learn.staff.img_url</code>. 头像
     */
    public final TableField<StaffRecord, String> IMG_URL = createField("img_url", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "头像");

    /**
     * The column <code>learn.staff.document_type</code>. 证件类型
     */
    public final TableField<StaffRecord, DocumentType> DOCUMENT_TYPE = createField("document_type", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "证件类型", new DocumentTypeConverter());

    /**
     * The column <code>learn.staff.document_number</code>. 证件号
     */
    public final TableField<StaffRecord, String> DOCUMENT_NUMBER = createField("document_number", org.jooq.impl.SQLDataType.VARCHAR.length(64).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "证件号");

    /**
     * The column <code>learn.staff.status</code>.
     */
    public final TableField<StaffRecord, StaffStatusType> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "", new StaffStatusTypeConverter());

    /**
     * The column <code>learn.staff.create_by</code>.
     */
    public final TableField<StaffRecord, Integer> CREATE_BY = createField("create_by", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>learn.staff.create_at</code>.
     */
    public final TableField<StaffRecord, LocalDateTime> CREATE_AT = createField("create_at", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>learn.staff.update_by</code>.
     */
    public final TableField<StaffRecord, Integer> UPDATE_BY = createField("update_by", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>learn.staff.update_at</code>.
     */
    public final TableField<StaffRecord, LocalDateTime> UPDATE_AT = createField("update_at", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>learn.staff.delete_by</code>.
     */
    public final TableField<StaffRecord, Integer> DELETE_BY = createField("delete_by", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>learn.staff.delete_at</code>.
     */
    public final TableField<StaffRecord, LocalDateTime> DELETE_AT = createField("delete_at", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>learn.staff.enabled</code>. 默认启用
     */
    public final TableField<StaffRecord, Byte> ENABLED = createField("enabled", org.jooq.impl.SQLDataType.TINYINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("1", org.jooq.impl.SQLDataType.TINYINT)), this, "默认启用");

    /**
     * Create a <code>learn.staff</code> table reference
     */
    public Staff() {
        this("staff", null);
    }

    /**
     * Create an aliased <code>learn.staff</code> table reference
     */
    public Staff(String alias) {
        this(alias, STAFF);
    }

    private Staff(String alias, Table<StaffRecord> aliased) {
        this(alias, aliased, null);
    }

    private Staff(String alias, Table<StaffRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Learn.LEARN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<StaffRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_STAFF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<StaffRecord> getPrimaryKey() {
        return Keys.KEY_STAFF_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<StaffRecord>> getKeys() {
        return Arrays.<UniqueKey<StaffRecord>>asList(Keys.KEY_STAFF_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Staff as(String alias) {
        return new Staff(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Staff rename(String name) {
        return new Staff(name, null);
    }
}
