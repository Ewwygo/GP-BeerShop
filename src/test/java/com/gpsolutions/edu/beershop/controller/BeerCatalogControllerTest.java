package com.gpsolutions.edu.beershop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class BeerCatalogControllerTest extends AbstractControllerTest {

    @Test
    public void testCatalogIsOk() throws Exception {
        mockMvc.perform(get("/beer-shop-app/catalog")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "      \"id\" : 1,\n" +
                        "      \"title\" : \"Goose\",\n" +
                        "      \"description\" : \"Strong\",\n" +
                        "      \"alco\" : \"5.7%\",\n" +
                        "      \"price\" : 5\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    public void testCatalogAddNewBeerIsOk() throws Exception {
        //given
        final String token = signInAsAdmin();
        //when
        mockMvc.perform(post("/beer-shop-app/catalog/add-new-beer").header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "  \"title\" : \"Goose\",\n" +
                                        "  \"description\" : \"Strong\",\n" +
                                        "  \"alco\" : \"5.7%\",\n" +
                                        "  \"density\" : \"10%\",\n" +
                                        "  \"price\" : 5\n" +
                                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCatalogAddNewBeerWhenAccessedByClient() throws Exception {
        //given
        final String token = signInAsClient();
        //when
        mockMvc.perform(post("/beer-shop-app/catalog/add-new-beer").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"title\" : \"Goose\",\n" +
                        "  \"description\" : \"Strong\",\n" +
                        "  \"alco\" : \"5.7%\",\n" +
                        "  \"density\" : \"10%\",\n" +
                        "  \"price\" : 5\n" +
                        "}"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testCatalogDeleteBeerIsOk() throws Exception {
        mockMvc.perform(post("/beer-shop-app/catalog/delete-beer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
