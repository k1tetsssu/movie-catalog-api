package com.moviesCatalog.movie_catalog_api.controller;

import com.moviesCatalog.movie_catalog_api.model.Movie;
import com.moviesCatalog.movie_catalog_api.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // это REST API контроллер, Он автоматически возвращает JSON.
@RequestMapping("/movies") // Базовый URL. Все методы будут начинаться с "/movies"


public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping // Получить все фильмы
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}") // Получить фильм по ID
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping // Создать фильм
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}") // Удалить фильм
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

}