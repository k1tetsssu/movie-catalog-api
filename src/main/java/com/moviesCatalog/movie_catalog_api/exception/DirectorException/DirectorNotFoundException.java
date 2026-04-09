package com.moviesCatalog.movie_catalog_api.exception.DirectorException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DirectorNotFoundException extends RuntimeException {
    public DirectorNotFoundException(Long id) {
        super("Режиссёр с ID " + id + " не найден!");
    }
}