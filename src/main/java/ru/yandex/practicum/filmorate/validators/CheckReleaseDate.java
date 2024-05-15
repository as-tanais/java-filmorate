package ru.yandex.practicum.filmorate.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = {FilmDateValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckReleaseDate {
    String message() default "Дата фильма не может быть раньше 28.12.1895";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
