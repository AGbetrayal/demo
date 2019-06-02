package com.core.data.jpa.commons.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AGbetrayal
 * @date 2019/5/31 21:39
 */
@Data
public final class Order implements Serializable {
    /**
     * 排序字段
     */
    private String field;

    /**
     * ASC | DESC
     */
    private boolean desc;

    public Order() {

    }

    public Order(String field, boolean desc) {
        this.field = field;
        this.desc = desc;
    }

    /**
     * ASC
     *
     * @param field
     * @return
     */
    public static Order asc(String field) {
        return new Order(field, false);
    }

    /**
     * DESC
     *
     * @param field
     * @return
     */
    public static Order desc(String field) {
        return new Order(field, true);
    }

    @Override
    public String toString() {
        return field + (desc ? " DESC" : " ASC");
    }
}
