package com.moviesCatalog.movie_catalog_api.repository;

import com.moviesCatalog.movie_catalog_api.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
