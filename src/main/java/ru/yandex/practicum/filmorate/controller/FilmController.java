package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final FilmStorage filmStorage;

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        log.info("Put request for film");
        return filmStorage.update(film);
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        return filmStorage.create(film);
    }

    @GetMapping()
    public Collection<Film> findAll() {
        log.info("Get request for films");
        return filmStorage.findAll();
    }

    @DeleteMapping(value = "/{filmId}")
    private void deleteFilm(@PathVariable Long filmId) {
        filmStorage.deleteFilm(filmId);
    }

    @PutMapping(value = "/{filmId}/like/{userId}")
    public void addLike(@PathVariable Long filmId,@PathVariable Long userId) {
        filmService.addLike(filmId, userId);
    }

    @DeleteMapping(value = "/{filmId}/like/{userId}")
    public void deleteLike(@PathVariable Long filmId, @PathVariable Long userId) {
        filmService.deleteLike(filmId, userId);
    }

    @GetMapping("/popular")
    public List<Film> getMostPopularFilms(@RequestParam(defaultValue = "10") Long count) {
        return filmService.getMostPopularFilms(count);
    }
}
