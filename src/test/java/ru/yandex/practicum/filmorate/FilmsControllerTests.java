package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.practicum.filmorate.controller.FilmController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmController.class)
class FilmsControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		mockMvc.perform(get("/films"))
				.andExpect(status().isOk());
	}

	@Test
	public void createFilmTest() throws Exception {
		String s = "{\n  \"name\": \"test\"," +
				"\n  \"description\": \"Description\"," +
				"\n  \"releaseDate\": \"1900-03-25\"," +
				"\n  \"duration\": 200\n}";
		mockMvc.perform(post("/films")
						.content(s)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isOk());
	}

	@Test
	public void createFilmFailReleaseDateTest() throws Exception {
		String s = "{\n" +
				"\n	 \"name\": \"test\"," +
				"\n  \"description\": \"Description\"," +
				"\n  \"releaseDate\": \"1700-03-25\"," +
				"\n  \"duration\": 100\n}";
		mockMvc.perform(post("/films")
						.content(s)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().is4xxClientError());
	}

}
