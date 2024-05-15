package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class User {
    Long id;
    @Email
    @NotBlank
    String email;
    @NotBlank(message = "Login не может быть пустым.")
    @NotNull
    String login;
    String name;
    @Past(message = "День рождения не может быть в дудующем.")
    LocalDate birthday;
}
