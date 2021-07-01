package com.bluenile.scrabble;

import com.bluenile.scrabble.controller.ScrabbleV1Interface;
import com.bluenile.scrabble.service.ScrabbleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.AssertTrue;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ScrabbleV1Interface.class)
@AutoConfigureMockMvc
class ScrabbleApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScrabbleService scrabbleService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ScrabbleService scrabbleServiceMock;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {

        when(scrabbleServiceMock.getScrabbleList(any(String.class))).thenReturn(Arrays.asList("Hello, World"));
        mockMvc.perform(get("/words/hat"))
                .andExpect(status().isOk());
    }

}
