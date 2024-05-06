package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    long id;
    @NotBlank
    String email;
    String login;
    String name;
    LocalDate birthday;
}
