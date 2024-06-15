package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        log.info("Put request for film");
        return filmService.update(film);
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        return filmService.create(film);
    }

    @GetMapping()
    public Collection<Film> findAll() {
        log.info("Get request for films");
        return filmService.findAll();
    }

    @PutMapping(value = "/{filmId}/like/{userId}")
    public void addLike(@NotNull @PathVariable long filmId, @NotNull @PathVariable long userId) {
        filmService.addLike(filmId, userId);
    }

    @DeleteMapping(value = "/{filmId}/like/{userId}")
    public void deleteLike(@NotNull @PathVariable long filmId, @NotNull @PathVariable long userId) {
        filmService.deleteLike(filmId, userId);
    }

    @GetMapping("/popular")
    public List<Film> getMostPopularFilms(@RequestParam(defaultValue = "10") int count) {
        return filmService.getMostPopularFilms(count);
    }
}
