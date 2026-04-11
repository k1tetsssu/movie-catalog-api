package com.moviesCatalog.movie_catalog_api.exception.ActorException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActorNotFoundException extends RuntimeException {
    public ActorNotFoundException(Long id) {
        super("Актёр с ID " + id + " не найден!");
    }
}