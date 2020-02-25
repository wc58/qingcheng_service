package com.qingcheng.pojo.order;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 组合体
 */
@Data
public class OrderDetails implements Serializable {

    private Order order;

    private List<OrderItem> orderItems;

}
