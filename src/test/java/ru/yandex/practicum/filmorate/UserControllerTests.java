package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.controller.UserController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsersTest() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void createUserTest() throws Exception {

        String s = "{\n" +
                "\"login\": \"as\",\n" +
                "  \"name\": \"Alex Samokhin\",\n" +
                "  \"email\": \"as@test.ru\",\n" +
                "  \"birthday\": \"1996-08-20\"\n" +
                "}";
        mockMvc.perform(post("/users")
                        .content(s)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk());

    }

    @Test
    public void createUserWithEmptyEmailTest() throws Exception {

        String s = "{\n" +
                "\"login\": \"as\",\n" +
                "  \"name\": \"Alex Samokhin\",\n" +
                "  \"birthday\": \"1996-08-20\"\n" +
                "}";
        mockMvc.perform(post("/users")
                        .content(s)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void createUserWithFailBithdayTest() throws Exception {

        String s = "{\n" +
                " \"login\": \"as\",\n" +
                " \"name\": \"Alex Samokhin\",\n" +
                "  \"email\": \"as@test.ru\",\n" +
                " \"birthday\": \"2050-08-20\"\n" +
                "}";
        mockMvc.perform(post("/users")
                        .content(s)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());



    }
}
