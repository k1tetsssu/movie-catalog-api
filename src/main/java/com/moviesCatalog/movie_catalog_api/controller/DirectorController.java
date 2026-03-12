package com.moviesCatalog.movie_catalog_api.controller;

import com.moviesCatalog.movie_catalog_api.model.Director;
import com.moviesCatalog.movie_catalog_api.service.DirectorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    public Director getDirectorById(@PathVariable Long id) {
        return directorService.getDirectorById(id);
    }

    @PostMapping
    public Director createDirector(@RequestBody Director director) {
        return directorService.saveDirector(director);
    }

    @PutMapping("/{id}") // ОБНОВЛЕНИЕ РЕЖИССЕРА
    public Director updateDirector(@PathVariable Long id, @RequestBody Director directorDetails) {
        Director director = directorService.getDirectorById(id);
        if (director != null) {
            director.setName(directorDetails.getName());
            director.setBirthYear(directorDetails.getBirthYear());
            return directorService.saveDirector(director);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
    }
}