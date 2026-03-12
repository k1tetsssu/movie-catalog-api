package com.moviesCatalog.movie_catalog_api.controller;

import com.moviesCatalog.movie_catalog_api.model.Actor;
import com.moviesCatalog.movie_catalog_api.service.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable Long id) {
        return actorService.getActorById(id);
    }

    @PostMapping
    public Actor createActor(@RequestBody Actor actor) {
        return actorService.saveActor(actor);
    }

    @PutMapping("/{id}") // ОБНОВЛЕНИЕ АКТЕРА
    public Actor updateActor(@PathVariable Long id, @RequestBody Actor actorDetails) {
        Actor actor = actorService.getActorById(id);
        if (actor != null) {
            actor.setName(actorDetails.getName());
            actor.setBirthYear(actorDetails.getBirthYear());
            return actorService.saveActor(actor);
        } else {
            return null; // можно возвращать 404 через ResponseEntity
        }
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
    }
}

