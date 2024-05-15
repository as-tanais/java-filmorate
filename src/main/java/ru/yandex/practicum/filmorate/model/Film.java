package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.yandex.practicum.filmorate.validators.CheckReleaseDate;

import java.time.LocalDate;

/**
 * Film.
 */

@Data
public class Film {
    Long id;
    @NotBlank(message = "Название не должно быть пустым")
    @NotNull
    String name;
    @Length(max = 200, message = "Максимальная длина описания — 200 символов")
    @NotNull
    String description;
    @CheckReleaseDate
    LocalDate releaseDate;
    @Positive
    Integer duration;
}
