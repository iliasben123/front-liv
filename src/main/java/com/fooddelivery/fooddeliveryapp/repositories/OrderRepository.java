package com.fooddelivery.fooddeliveryapp.repositories;

import com.fooddelivery.fooddeliveryapp.entities.Order;  // Import the correct Order entity
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
