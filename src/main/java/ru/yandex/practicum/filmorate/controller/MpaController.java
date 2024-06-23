package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.MpaService;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/mpa")
@RequiredArgsConstructor
public class MpaController {

    private final MpaService mpaService;

    @GetMapping
    public ArrayList<Mpa> getAllMpa() {
        return new ArrayList<>(mpaService.getAllMpa());
    }

    @GetMapping("/{id}")
    public Optional<Mpa> getById(@PathVariable Long id) {
        return mpaService.getById(id);
    }
}
