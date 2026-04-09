package com.moviesCatalog.movie_catalog_api.exception.GenreException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Long id) {
        super("Жанр с ID " + id + " не найден!");
    }
}
