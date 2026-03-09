package com.moviesCatalog.movie_catalog_api.repository;

import com.moviesCatalog.movie_catalog_api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
