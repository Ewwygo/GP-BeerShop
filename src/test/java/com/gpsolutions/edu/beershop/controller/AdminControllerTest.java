package com.gpsolutions.edu.beershop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AdminControllerTest extends AbstractControllerTest {

    @Test
    public void testOrdersIsOk() throws Exception {
        //given
        final String token = signInAsAdmin();
        //when
        mockMvc.perform(get("/beer-shop-app/admin/orders").header("Authorization",token))
                //then
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
    public void testOrdersWhenAccessedByClient() throws Exception {
        //given
        final String token = signInAsClient();
        //when
        mockMvc.perform(get("/beer-shop-app/admin/orders").header("Authorization",token))
                //then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testOrdersWhenNotAuthorized() throws Exception {
        //given
        //when
        mockMvc.perform(get("/beer-shop-app/admin/orders"))
                //then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testOrdersCompleteOrderIsOk() throws Exception {
        //given
        final String token = signInAsAdmin();
        //when
        mockMvc.perform(post("/beer-shop-app/admin/orders/1/complete-order").header("Authorization",token))
                .andExpect(status().isOk());
    }

    @Test
    public void testOrdersCompleteOrderWhenAccessedByClient() throws Exception {
        //given
        final String token = signInAsClient();
        //when
        mockMvc.perform(post("/beer-shop-app/admin/orders/1/complete-order").header("Authorization",token))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testOrdersCompleteOrderWhenNotAuthorized() throws Exception {
        //given
        //when
        mockMvc.perform(post("/beer-shop-app/admin/orders/1/complete-order"))
                .andExpect(status().isForbidden());
    }
}
