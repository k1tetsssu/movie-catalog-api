package com.moviesCatalog.movie_catalog_api.repository;

import com.moviesCatalog.movie_catalog_api.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
