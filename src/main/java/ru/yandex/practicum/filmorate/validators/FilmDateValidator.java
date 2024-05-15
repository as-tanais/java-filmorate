package ru.yandex.practicum.filmorate.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class FilmDateValidator implements ConstraintValidator<CheckReleaseDate, LocalDate> {
    private static final LocalDate firstFilmReleaseDate = LocalDate.of(1895, 12, 28);

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.isAfter(firstFilmReleaseDate);
    }
}
