package com.moviesCatalog.movie_catalog_api.repository;

import com.moviesCatalog.movie_catalog_api.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
