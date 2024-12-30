package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.Order;
import com.fooddelivery.fooddeliveryapp.entities.Status;
import com.fooddelivery.fooddeliveryapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    // Constructor injection (removes field injection)
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }


    @Override
    public Order updateOrderStatus(Long orderId, Status status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersForUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
