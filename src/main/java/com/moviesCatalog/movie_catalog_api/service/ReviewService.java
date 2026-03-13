package com.moviesCatalog.movie_catalog_api.service;

import com.moviesCatalog.movie_catalog_api.model.Review;
import com.moviesCatalog.movie_catalog_api.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Получить все отзывы
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Получить отзыв по id
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    // Создать отзыв
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    // Удалить отзыв
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    // Обновить отзыв
    public Review updateReview(Long id, Review updatedReview) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setComment(updatedReview.getComment());
                    review.setRating(updatedReview.getRating());
                    return reviewRepository.save(review);
                })
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }
}