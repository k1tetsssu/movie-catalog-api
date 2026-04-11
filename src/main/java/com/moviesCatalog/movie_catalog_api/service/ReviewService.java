package com.moviesCatalog.movie_catalog_api.service;

import com.moviesCatalog.movie_catalog_api.exception.ReviewException.InvalidReviewDataException;
import com.moviesCatalog.movie_catalog_api.exception.ReviewException.ReviewNotFoundException;
import com.moviesCatalog.movie_catalog_api.exception.ReviewException.DuplicateReviewException;
import com.moviesCatalog.movie_catalog_api.model.Review;
import com.moviesCatalog.movie_catalog_api.model.Movie;
import com.moviesCatalog.movie_catalog_api.repository.ReviewRepository;
import com.moviesCatalog.movie_catalog_api.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    public Review saveReview(Review review) {
        validateReview(review);

        if (reviewRepository.existsByAuthorAndMovie_Id(review.getAuthor(), review.getMovie().getId())) {
            throw new DuplicateReviewException("Рецензия от автора '" + review.getAuthor() + "' на этот фильм уже существует");
        }

        if (!movieRepository.existsById(review.getMovie().getId())) {
            throw new InvalidReviewDataException("Фильм с ID " + review.getMovie().getId() + " не найден");
        }

        review.setAuthor(review.getAuthor().trim());
        review.setComment(review.getComment().trim());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException(id);
        }
        reviewRepository.deleteById(id);
    }

    public Review updateReview(Long id, Review updatedReview) {
        validateReview(updatedReview);

        return reviewRepository.findById(id)
                .map(review -> {
                    if (reviewRepository.existsByAuthorAndMovie_IdAndIdNot(
                            updatedReview.getAuthor(),
                            updatedReview.getMovie().getId(),
                            id)) {
                        throw new DuplicateReviewException("Рецензия от автора '" + updatedReview.getAuthor() + "' на этот фильм уже существует");
                    }

                    if (!movieRepository.existsById(updatedReview.getMovie().getId())) {
                        throw new InvalidReviewDataException("Фильм с ID " + updatedReview.getMovie().getId() + " не найден");
                    }

                    review.setAuthor(updatedReview.getAuthor().trim());
                    review.setComment(updatedReview.getComment().trim());
                    review.setRating(updatedReview.getRating());
                    review.setMovie(updatedReview.getMovie());
                    return reviewRepository.save(review);
                })
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    // ← PATCH метод ВНУТРИ класса
    public Review patchReview(Long id, Map<String, Object> updates) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));

        if (updates.containsKey("author")) {
            String newAuthor = (String) updates.get("author");
            if (newAuthor == null || newAuthor.trim().isEmpty()) {
                throw new InvalidReviewDataException("Имя автора не должно быть пустым");
            }
            review.setAuthor(newAuthor.trim());
        }

        if (updates.containsKey("comment")) {
            String newComment = (String) updates.get("comment");
            if (newComment == null || newComment.trim().isEmpty()) {
                throw new InvalidReviewDataException("Текст рецензии не должен быть пустым");
            }
            review.setComment(newComment.trim());
        }

        if (updates.containsKey("rating")) {
            Integer newRating = (Integer) updates.get("rating");
            if (newRating == null || newRating < 1 || newRating > 10) {
                throw new InvalidReviewDataException("Рейтинг должен быть от 1 до 10");
            }
            review.setRating(newRating);
        }

        if (updates.containsKey("movie")) {
            Map<String, Object> movieData = (Map<String, Object>) updates.get("movie");
            if (movieData != null && movieData.containsKey("id")) {
                Long movieId = ((Number) movieData.get("id")).longValue();
                if (!movieRepository.existsById(movieId)) {
                    throw new InvalidReviewDataException("Фильм с ID " + movieId + " не найден");
                }
                Movie movie = movieRepository.findById(movieId)
                        .orElseThrow(() -> new InvalidReviewDataException("Фильм с ID " + movieId + " не найден"));
                review.setMovie(movie);
            }
        }

        return reviewRepository.save(review);
    }

    private void validateReview(Review review) {
        if (review.getAuthor() == null || review.getAuthor().trim().isEmpty()) {
            throw new InvalidReviewDataException("Имя автора не должно быть пустым");
        }
        if (review.getComment() == null || review.getComment().trim().isEmpty()) {
            throw new InvalidReviewDataException("Текст рецензии не должен быть пустым");
        }
        if (review.getRating() == null || review.getRating() < 1 || review.getRating() > 10) {
            throw new InvalidReviewDataException("Рейтинг должен быть от 1 до 10");
        }
    }
}