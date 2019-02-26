/*
 * This file is generated by jOOQ.
*/
package com.ccbcfx.learn.tables;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Learn extends SchemaImpl {

    private static final long serialVersionUID = 1869129705;

    /**
     * The reference instance of <code>learn</code>
     */
    public static final Learn LEARN = new Learn();

    /**
     * The table <code>learn.schema_version</code>.
     */
    public final SchemaVersion SCHEMA_VERSION = com.ccbcfx.learn.tables.SchemaVersion.SCHEMA_VERSION;

    /**
     * The table <code>learn.staff</code>.
     */
    public final Staff STAFF = com.ccbcfx.learn.tables.Staff.STAFF;

    /**
     * No further instances allowed
     */
    private Learn() {
        super("learn", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            SchemaVersion.SCHEMA_VERSION,
            Staff.STAFF);
    }
}
