package com.gpsolutions.edu.beershop;

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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AdminController {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOrdersIsOk() throws Exception {
        mockMvc.perform(get("/beer-shop-app/admin/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "      \"id\" : 1,\n" +
                        "      \"clientName\" : \"Alex\",\n" +
                        "      \"date\" : \"04.02.2020\",\n" +
                        "      \"Beer\" : \"Goose x 2, Kozel x 3\",\n" +
                        "      \"totalCost\" : 22\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    public void testOrdersCompleteOrderIsOk() throws Exception {
        mockMvc.perform(post("/beer-shop-app/admin/orders/1/complete-order")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
