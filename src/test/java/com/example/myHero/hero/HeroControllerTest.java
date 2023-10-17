package com.example.myHero.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
