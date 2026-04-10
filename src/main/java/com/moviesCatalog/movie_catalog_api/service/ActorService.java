package com.moviesCatalog.movie_catalog_api.service;

import com.moviesCatalog.movie_catalog_api.exception.ActorException.ActorNotFoundException;
import com.moviesCatalog.movie_catalog_api.exception.ActorException.InvalidActorDataException;
import com.moviesCatalog.movie_catalog_api.exception.ActorException.DuplicateActorException;
import com.moviesCatalog.movie_catalog_api.model.Actor;
import com.moviesCatalog.movie_catalog_api.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Actor getActorById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    public Actor saveActor(Actor actor) {
        validateActor(actor);

        if (actorRepository.existsByName(actor.getName().trim())) {
            throw new DuplicateActorException("Актёр с именем '" + actor.getName() + "' уже существует");
        }

        actor.setName(actor.getName().trim());
        return actorRepository.save(actor);
    }

    public Actor updateActor(Long id, Actor updatedActor) {
        validateActor(updatedActor);

        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));

        String newName = updatedActor.getName().trim();

        if (actorRepository.existsByNameAndIdNot(newName, id)) {
            throw new DuplicateActorException("Актёр с именем '" + newName + "' уже существует");
        }

        actor.setName(newName);
        actor.setBirthYear(updatedActor.getBirthYear());

        return actorRepository.save(actor);
    }

    public void deleteActor(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));

        actorRepository.delete(actor);
    }

    private void validateActor(Actor actor) {
        if (actor.getName() == null || actor.getName().trim().isEmpty()) {
            throw new InvalidActorDataException("Имя актёра не должно быть пустым");
        }

        if (actor.getBirthYear() == null || actor.getBirthYear() < 1850 || actor.getBirthYear() > 2100) {
            throw new InvalidActorDataException("Некорректный год рождения актёра");
        }
    }
}