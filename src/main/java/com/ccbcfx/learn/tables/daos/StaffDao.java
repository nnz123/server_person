
package com.ccbcfx.learn.tables.daos;


import com.ccbcfx.learn.tables.pojos.Staff;
import org.jooq.*;
import org.jooq.types.UInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ccbcfx.learn.tables.Staff.STAFF;

@Component
public class StaffDao {
    @Autowired
    DSLContext dslContext;

    /**
     * 插入一条数据
     *
     * @param staff
     * @return 插入数量
     */
    public int addStaff(Staff staff) {
        int res = dslContext.insertInto(STAFF)
                .set(STAFF.NAME, staff.getName())
                .set(STAFF.GENDER, staff.getGender())
                .set(STAFF.STATUS, staff.getStatus())
                .set(STAFF.DOCUMENT_TYPE, staff.getDocumentType())
                .set(STAFF.DOCUMENT_NUMBER, staff.getDocumentNumber())
                .set(STAFF.CREATE_BY, staff.getCreateBy())
                .set(STAFF.CREATE_AT, staff.getCreateAt())
                .execute();
        return res;
    }

    /**
     * 根据主键查询数据
     *
     * @param id
     * @return
     */
    public Staff findById(UInteger id) {
        Field<?>[] fields =
                {STAFF.ID,
                        STAFF.NAME,
                        STAFF.GENDER,
                        STAFF.PHONE,
                        STAFF.STATUS,
                        STAFF.DOCUMENT_TYPE,
                        STAFF.IMG_URL,
                        STAFF.DOCUMENT_NUMBER,
                        STAFF.CREATE_BY,
                        STAFF.CREATE_AT,
                        STAFF.UPDATE_BY,
                        STAFF.UPDATE_AT,
                        STAFF.DELETE_BY,
                        STAFF.DELETE_AT};
        SelectConditionStep<Record> result = dslContext.select(fields).from(STAFF).where(STAFF.ID.eq(id));
        return result.fetchOneInto(Staff.class);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<Staff> findAll() {
        List staffs = new ArrayList<Staff>();
        Result<Record1<UInteger>> result = dslContext.select(STAFF.ID).from(STAFF).fetch();
        for (Record1<UInteger> record : result) {
            staffs.add(findById(record.getValue(STAFF.ID)));
        }
        return staffs;
    }

    /**
     * 分页查询
     *
     * @param offset 起始行
     * @param size   page size
     * @return 查询结果
     */
    public List<Staff> findPage(int offset, int size) {
        List<Staff> staffs = new ArrayList<Staff>();
        Result<Record1<UInteger>> result = dslContext.select(STAFF.ID).from(STAFF).limit(offset, size).fetch();
        for (Record1<UInteger> record : result) {
            staffs.add(findById(record.getValue(STAFF.ID)));
        }
        return staffs;
    }

    /**
     * 修改指定id的名字
     *
     * @param id
     * @param name
     * @return
     */
    public int update(UInteger id, String name) {
        UpdateSetMoreStep updateSetMoreStep = dslContext.update(STAFF).set(STAFF.NAME, name);
        return updateSetMoreStep.where(STAFF.ID.eq(id)).execute();
    }

    /**
     * 修改指定id的员工
     *
     * @param params
     * @param id
     * @return
     */
    public int update(Map<TableField, Object> params, UInteger id) {
        UpdateQuery updateQuery = dslContext.updateQuery(STAFF);
        for (TableField field : params.keySet()) {
            updateQuery.addValue(field, params.get(field));
        }
        return updateQuery.execute();
    }

    /**
     * 根据查询条件获取数据
     *
     * @param conditions
     * @return
     */
    public List<Staff> findStaffByConditions(Condition[] conditions) {
        List staffs = new ArrayList<Staff>();
        SelectQuery selectQuery = dslContext.selectQuery();
        selectQuery.addSelect(STAFF.ID);
        selectQuery.addConditions(conditions);
        selectQuery.addFrom(STAFF);
        Result<Record1<UInteger>> records = selectQuery.fetch();
        for (Record1<UInteger> record : records) {
            staffs.add(findById(record.getValue(STAFF.ID)));
        }
        return staffs;
    }

    /**
     * 删除指定主键的记录
     *
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return dslContext.delete(STAFF).where(STAFF.ID.eq(UInteger.valueOf(id))).execute() > 0 ? true : false;
    }
}
