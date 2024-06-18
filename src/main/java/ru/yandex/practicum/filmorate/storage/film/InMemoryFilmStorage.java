package ru.yandex.practicum.filmorate.storage.film;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exception.ConditionsNotMetException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Long, Film> films = new HashMap<>();

    @Override
    public Collection<Film> findAll() {
        return films.values();
    }

    @Override
    public Film create(@Valid @RequestBody Film film) {
        film.setId(getNextId());
        films.put(film.getId(), film);
        log.info("Create Film: " + film);
        return film;
    }

    @Override
    public Film update(@Valid @RequestBody Film newFilm) {
        if (newFilm.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }
        if (films.containsKey(newFilm.getId())) {
            Film oldFilm = films.get(newFilm.getId());
            if (!(newFilm.getName() == null)) {
                oldFilm.setName(newFilm.getName());
            }
            if (!(newFilm.getDescription() == null)) {
                oldFilm.setDescription(newFilm.getDescription());
            }
            if (!(newFilm.getReleaseDate() == null)) {
                oldFilm.setReleaseDate(newFilm.getReleaseDate());
            }
            if (!(newFilm.getDuration() == null)) {
                oldFilm.setDuration(newFilm.getDuration());
            }
            return oldFilm;
        }
        throw new NotFoundException("Film с ID: " + newFilm.getId() + " не найден.");
    }

    @Override
    public Film findFilmById(long id) {
        if (films.containsKey(id)) {
            return films.get(id);
        }else {
            throw new NotFoundException("Film не найден");
        }
    }


    @Override
    public void deleteFilm(long filmId) {
        Film film = films.get(filmId);
        if (film != null) {
            films.remove(filmId);
        } else {
            throw new NotFoundException("Такого фильма нет");
        }
    }

    private long getNextId() {
        long currentMaxId = films.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }


}
