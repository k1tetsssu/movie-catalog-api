package com.moviesCatalog.movie_catalog_api.service;

import com.moviesCatalog.movie_catalog_api.exception.DirectorException.DirectorNotFoundException;
import com.moviesCatalog.movie_catalog_api.exception.DirectorException.InvalidDirectorDataException;
import com.moviesCatalog.movie_catalog_api.exception.DirectorException.DuplicateDirectorException;
import com.moviesCatalog.movie_catalog_api.model.Director;
import com.moviesCatalog.movie_catalog_api.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public Director getDirectorById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));
    }

    public Director saveDirector(Director director) {
        validateDirector(director);

        String directorName = director.getName().trim();

        if (directorRepository.existsByName(directorName)) {
            throw new DuplicateDirectorException("Режиссёр с именем '" + directorName + "' уже существует");
        }

        director.setName(directorName);
        return directorRepository.save(director);
    }

    public Director updateDirector(Long id, Director updatedDirector) {
        validateDirector(updatedDirector);

        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));

        String newName = updatedDirector.getName().trim();

        if (directorRepository.existsByNameAndIdNot(newName, id)) {
            throw new DuplicateDirectorException("Режиссёр с именем '" + newName + "' уже существует");
        }

        director.setName(newName);
        director.setBirthYear(updatedDirector.getBirthYear());

        return directorRepository.save(director);
    }

    public void deleteDirector(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));

        directorRepository.delete(director);
    }

    private void validateDirector(Director director) {
        if (director.getName() == null || director.getName().trim().isEmpty()) {
            throw new InvalidDirectorDataException("Имя режиссёра не должно быть пустым");
        }

        if (director.getBirthYear() == null || director.getBirthYear() < 1850 || director.getBirthYear() > 2100) {
            throw new InvalidDirectorDataException("Некорректный год рождения режиссёра");
        }
    }
}