package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.Review;
import com.fooddelivery.fooddeliveryapp.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsForDish(Long dishId) {
        return reviewRepository.findByDishId(dishId);
    }
}
