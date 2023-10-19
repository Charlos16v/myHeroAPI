package com.example.myHero.hero;

import com.example.myHero.repository.HeroRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HeroRepository heroRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetAllHeroes() throws Exception {

        mockMvc.perform(get("/hero")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"id\":1,\"name\":\"Superman\",\"description\":\"Man of Steel\",\"dob\":\"1980-01-01\",\"age\":43},{\"id\":2,\"name\":\"Batman\",\"description\":\"Dark Knight\",\"dob\":\"1975-03-15\",\"age\":48},{\"id\":3,\"name\":\"Wonder Woman\",\"description\":\"Amazon Princess\",\"dob\":\"1985-06-10\",\"age\":38},{\"id\":4,\"name\":\"The Flash\",\"description\":\"Speedster\",\"dob\":\"1990-12-05\",\"age\":32},{\"id\":5,\"name\":\"Aquaman\",\"description\":\"King of Atlantis\",\"dob\":\"1988-08-20\",\"age\":35}]"));
    }

    @Test
    public void testGetHeroByIdFound() throws Exception {
        Long heroId = 1L;

        mockMvc.perform(get("/hero/" + heroId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetHeroByIdNotFound() throws Exception {
        Long heroId = 100L; // Assuming this ID does not exist in the test data

        mockMvc.perform(get("/hero/" + heroId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSearchHeroesByNameCaseInsensitive() throws Exception {
        mockMvc.perform(get("/hero/search")
                        .param("searchParameter", "MAN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Superman")))
                .andExpect(content().string(containsString("Batman")));
    }

    @Test
    public void testSearchHeroesByNameCaseInsensitiveNoResults() throws Exception {
        mockMvc.perform(get("/hero/search")
                        .param("searchParameter", "Thor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[]"));
    }

    @Test
    @Transactional
    public void testCreateHero() throws Exception {
        String requestBody = "{ \"name\": \"Test Man\", \"description\": \"The testing man.\", \"dob\": \"2001-10-01\" }";

        mockMvc.perform(post("/hero")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Test Man")))
                .andExpect(content().string(containsString("The testing man")));
    }

    @Test
    @Transactional
    public void testModifyHeroSuccess() throws Exception {
        Long heroId = 1L;
        String requestBody = "{ \"name\": \"Updated Superman\", \"description\": \"Updated Man of Steel\", \"dob\": \"1990-01-01\" }";

        mockMvc.perform(put("/hero/" + heroId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Updated Superman")))
                .andExpect(content().string(containsString("Updated Man of Steel")));
    }

    @Test
    public void testModifyHeroNotFound() throws Exception {
        Long heroId = 33L;
        String requestBody = "{ \"name\": \"Updated Superman\", \"description\": \"Updated Man of Steel\", \"dob\": \"1990-01-01\" }";

        mockMvc.perform(put("/hero/" + heroId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void testDeleteHeroByIdSuccess() throws Exception {
        Long heroId = 1L;

        mockMvc.perform(delete("/hero/" + heroId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteHeroByIdNotFound() throws Exception {
        Long heroId = 9999L; // Assuming this ID does not exist in the test data

        mockMvc.perform(delete("/hero/" + heroId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
