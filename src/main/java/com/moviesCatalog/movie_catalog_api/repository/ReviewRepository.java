package com.moviesCatalog.movie_catalog_api.repository;

import com.moviesCatalog.movie_catalog_api.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Методы должны быть ВНУТРИ интерфейса!
    boolean existsByAuthorAndMovie_Id(String author, Long movieId);
    boolean existsByAuthorAndMovie_IdAndIdNot(String author, Long movieId, Long excludeId);
}