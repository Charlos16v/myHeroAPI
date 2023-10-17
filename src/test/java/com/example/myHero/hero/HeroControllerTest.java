package com.example.myHero.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class HeroControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HeroService heroService;

    @InjectMocks
    private HeroController heroController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(heroController).build();
    }

    @Test
    public void testGetAllHeroes() throws Exception {
        List<Hero> defaultHeroes = Arrays.asList(
                new Hero(1L, "Superman", "Man of Steel", LocalDate.parse("1980-01-01")),
                new Hero(2L, "Batman", "Dark Knight", LocalDate.parse("1975-03-15")),
                new Hero(3L, "Wonder Woman", "Amazon Princess", LocalDate.parse("1985-06-10")),
                new Hero(4L, "The Flash", "Speedster", LocalDate.parse("1990-12-05")),
                new Hero(5L, "Aquaman", "King of Atlantis", LocalDate.parse("1988-08-20"))
        );


        when(heroService.getAll()).thenReturn(defaultHeroes);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String heroesJson = objectMapper.writeValueAsString(defaultHeroes);

        mockMvc.perform(get("/hero")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(heroesJson));
    }
}


