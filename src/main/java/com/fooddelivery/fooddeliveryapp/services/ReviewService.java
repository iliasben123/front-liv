package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.Review;

import java.util.List;

public interface ReviewService {
    Review addReview(Review review);

    List<Review> getReviewsForDish(Long dishId);
}
