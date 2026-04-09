package com.moviesCatalog.movie_catalog_api.exception.MovieException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(Long id) {
        super("Фильм с ID " + id + " не найден!");
    }
}
