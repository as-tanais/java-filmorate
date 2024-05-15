package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private Long id;
    @Email
    @NotBlank
    private String email;
    @NotBlank(message = "Login не может быть пустым.")
    @NotNull
    private String login;
    private String name;
    @Past(message = "День рождения не может быть в дудующем.")
    private LocalDate birthday;
}
