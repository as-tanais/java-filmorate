package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmDbStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {

    private final FilmDbStorage filmDbStorage;
    private final UserStorage userStorage;


    public List<Film> getAllFilm() {
        return filmDbStorage.getAllFilm();
    }

    public Film create (Film film){
        return filmDbStorage.create(film);
    }

    public Optional<Film> findFilmById(Long id){
        if (filmDbStorage.findFilmById(id).isEmpty()) {
            throw new NotFoundException("Фильм не найден");
        }
        return filmDbStorage.findFilmById(id);
    }

//    public void addLike(long filmId, long userId) {
//        Film film = filmStorage.findFilmById(filmId);
//        User user = userStorage.findUserById(userId);
//        if (film != null && user != null) {
//            film.getLikes().add(userId);
//        } else {
//            throw new NotFoundException("Фильм или User не найден");
//        }
//    }

//    public void deleteLike(long filmId, long userId) {
//        Film film = filmStorage.findFilmById(filmId);
//        User user = userStorage.findUserById(userId);
//        if (film != null && user != null) {
//            film.getLikes().remove(userId);
//        } else {
//            throw new NotFoundException("Фильм или User не найден");
//        }
//    }
//
//    public List<Film> getMostPopularFilms(int count) {
//        return filmStorage.findAll().stream()
//                .sorted((f1, f2) -> f2.getLikes().size() - f1.getLikes().size())
//                .limit(count)
//                .collect(Collectors.toList());
//    }
}
