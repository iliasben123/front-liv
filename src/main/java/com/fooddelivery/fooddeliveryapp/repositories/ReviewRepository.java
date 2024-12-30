package com.fooddelivery.fooddeliveryapp.repositories;

import com.fooddelivery.fooddeliveryapp.entities.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByDishId(Long dishId);
}
