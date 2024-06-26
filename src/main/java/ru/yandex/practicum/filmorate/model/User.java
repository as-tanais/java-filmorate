package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class User {
    private Long id;
    @Email
    @NotBlank
    private String email;
    @NotBlank(message = "Login не может быть пустым.")
    private String login;
    private String name;
    @Past(message = "День рождения не может быть в дудующем.")
    private LocalDate birthday;
    private Set<Long> friends = new LinkedHashSet<>();
}
