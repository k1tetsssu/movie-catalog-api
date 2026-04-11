package com.moviesCatalog.movie_catalog_api.exception.ReviewException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(Long id) {
        super("Рецензия с ID " + id + " не найдена!");
    }
}