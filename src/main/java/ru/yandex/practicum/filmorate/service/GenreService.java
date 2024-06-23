package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.genres.GenreStorage;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreStorage genreStorage;

    public Optional<Genre> getById(Long id) {
        if (genreStorage.getById(id).isEmpty()) {
            throw new NotFoundException("Жанр не найден");
        }
        return genreStorage.getById(id);
    }

    public List<Genre> getAll() {
        return genreStorage.getAll();
    }
}
