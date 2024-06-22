package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.yandex.practicum.filmorate.validators.CheckReleaseDate;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Film.
 */

@Data
public class Film {
    private Long id;
    @NotBlank(message = "Название не должно быть пустым")
    private String name;
    @Length(max = 200, message = "Максимальная длина описания — 200 символов")
    @NotNull
    private String description;
    @CheckReleaseDate
    private LocalDate releaseDate;
    @Positive
    private Integer duration;
    private Mpa mpa;
    private Set<Long> likes = new LinkedHashSet<>();
}
