package com.moviesCatalog.movie_catalog_api.service;

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
        return directorRepository.findById(id).orElse(null);
    }

    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    public Director updateDirector(Long id, Director updatedDirector) {

        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found"));

        director.setName(updatedDirector.getName());
        director.setBirthYear(updatedDirector.getBirthYear());

        return directorRepository.save(director);
    }

    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }
}
