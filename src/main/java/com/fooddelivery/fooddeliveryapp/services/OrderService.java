package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.Order;
import com.fooddelivery.fooddeliveryapp.entities.Status;

import java.util.List;

public interface OrderService {
    Order placeOrder(Order order);

    Order updateOrderStatus(Long orderId, Status status);

    List<Order> getOrdersForUser(Long userId);
}
