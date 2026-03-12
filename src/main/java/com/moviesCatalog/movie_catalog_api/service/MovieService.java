package com.moviesCatalog.movie_catalog_api.service;

import com.moviesCatalog.movie_catalog_api.model.Movie;
import com.moviesCatalog.movie_catalog_api.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // аннотация, показывающая что это сервисный компонент
public class MovieService {

    private final MovieRepository movieRepository; // Это ссылка на Repository, который работает с базой данных.
    // Конструктор
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    } // Spring автоматически передаст Repository - Dependency Injection

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    } // SELECT * FROM movies

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    } // SELECT * FROM movies WHERE id = ?

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    } // INSERT INTO movies ...


    public Movie updateMovie(Long id, Movie updatedMovie) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setTitle(updatedMovie.getTitle());
        movie.setReleaseYear(updatedMovie.getReleaseYear());
        movie.setRating(updatedMovie.getRating());

        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {

        movieRepository.deleteById(id);
    } // DELETE FROM movies WHERE id = ?
}