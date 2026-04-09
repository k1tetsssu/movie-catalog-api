package com.moviesCatalog.movie_catalog_api.service;

import com.moviesCatalog.movie_catalog_api.exception.MovieException.DuplicateMovieException;
import com.moviesCatalog.movie_catalog_api.exception.MovieException.InvalidMovieDataException;
import com.moviesCatalog.movie_catalog_api.exception.MovieException.MovieNotFoundException;
import com.moviesCatalog.movie_catalog_api.model.Movie;
import com.moviesCatalog.movie_catalog_api.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service // аннотация, показывающая что это сервисный компонент
public class MovieService {

    private final MovieRepository movieRepository; // Это ссылка на Repository, который работает с базой данных.

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    } // SELECT * FROM movies

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    } // SELECT * FROM movies WHERE id = ?

    public Movie saveMovie(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            throw new InvalidMovieDataException("Название фильма не может быть пустым");
        }

        if (movie.getRating() < 0 || movie.getRating() > 10) {
            throw new InvalidMovieDataException("Рейтинг должен быть от 0 до 10");
        }

        if (movie.getReleaseYear() < 1900) {
            throw new InvalidMovieDataException("Год выпуска некорректен");
        }
        if (movieRepository.existsByTitle(movie.getTitle())) {
            throw new DuplicateMovieException("Фильм с таким названием уже существует");
        }

        return movieRepository.save(movie);
    } // INSERT INTO movies ...


    public Movie updateMovie(Long id, Movie updatedMovie) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        movie.setTitle(updatedMovie.getTitle());
        movie.setReleaseYear(updatedMovie.getReleaseYear());
        movie.setRating(updatedMovie.getRating());

        if (movieRepository.existsByTitle(movie.getTitle())) {
            throw new DuplicateMovieException("Фильм с таким названием уже существует");
        }

        if (movie.getRating() < 0 || movie.getRating() > 10) {
            throw new InvalidMovieDataException("Рейтинг должен быть от 0 до 10");
        }
        if (movie.getReleaseYear() < 1900) {
            throw new InvalidMovieDataException("Год выпуска некорректен");
        }
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        if(!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }
        movieRepository.deleteById(id);
    } // DELETE FROM movies WHERE id = ?

}