package com.gpsolutions.edu.beershop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testClientSignUpIsCreated() throws Exception {
        // given
        // when

        mockMvc.perform(post("/client/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "  \"email\" : \"vasya@email.com\",\n" +
                                    "  \"password\" : \"qwerty\",\n" +
                                    "  \"fio\" : \"Пупкин Василий Иванович\",\n" +
                                    "  \"phoneNumber\" : \"+3752912345678\",\n" +
                                    "  \"info\" : \"some info\" \n" +
                                    "}"))
                //then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1\n" +
                        "}"));
    }


}
