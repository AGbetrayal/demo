package com.core.data.jpa.commons.utils;

import com.core.commons.util.ArrayUtils;
import com.core.commons.util.CollectionUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author AGbetrayal
 * @date 2019/5/31 21:38
 */
public abstract class OrderUtils {
    /**
     * 转换为Spring Data Sort
     *
     * @param orders orders
     * @return {@link Sort}
     */
    public static Sort toSort(List<Order> orders) {
        Sort sort = Sort.unsorted();
        if (CollectionUtils.isNotEmpty(orders)) {
            List<Sort.Order> orderList = orders.stream()
                    .filter(order -> Objects.nonNull(SqlEscapeUtils.escape(order.getField())))
                    .map(order -> new Sort.Order(order.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, order.getField()))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(orderList)) {
                sort = Sort.by(orderList);
            }
        }
        return sort;
    }
    /**
     * 转换为Spring Data Sort
     *
     * @param orders orders
     * @return {@link Sort}
     */
    public static Sort toSort(Order... orders) {
        return toSort(ArrayUtils
                .asArrayList(orders));
    }

    /**
     * Sort 转换为  Order
     *
     * @param sort {@link Sort}
     * @return 排序字段
     */
    public static List<Order> toOrderList(Sort sort) {
        List<Order> orders = new ArrayList<>();
        Iterator<Sort.Order> iterator = sort.iterator();
        iterator.forEachRemaining(item -> orders.add(new Order(item.getProperty(), item.isDescending())));
        return orders;
    }
}
