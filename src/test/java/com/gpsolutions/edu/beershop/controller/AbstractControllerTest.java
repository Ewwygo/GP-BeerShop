package com.gpsolutions.edu.beershop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpsolutions.edu.beershop.dto.UserSignInResponse;
import com.gpsolutions.edu.beershop.security.LoadClientDetailService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.gpsolutions.edu.beershop.security.Roles.ADMIN;
import static com.gpsolutions.edu.beershop.security.Roles.CLIENT;
import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @SpyBean
    protected LoadClientDetailService loadClientDetailService;

    protected String signInAsClient() throws Exception {
        final User user = new User("vasya@email.com", passwordEncoder.encode("qwerty"),
                List.of(new SimpleGrantedAuthority("ROLE_" + CLIENT.name())));
        willReturn(user).given(loadClientDetailService).loadUserByUsername("vasya@email.com");
        final String response = mockMvc.perform(post("/beer-shop-app/client/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"vasya@email.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(144)))
                .andReturn().getResponse().getContentAsString();
        return "Bearer " + objectMapper.readValue(response, UserSignInResponse.class).getToken();
    }

    protected String signInAsAdmin() throws Exception {
        final User user = new User("admin@email.com", passwordEncoder.encode("qwerty"),
                List.of(new SimpleGrantedAuthority("ROLE_" + ADMIN.name())));
        willReturn(user).given(loadClientDetailService).loadUserByUsername("admin@email.com");
        final String response = mockMvc.perform(post("/beer-shop-app/client/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"admin@email.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(144)))
                .andReturn().getResponse().getContentAsString();
        return "Bearer " + objectMapper.readValue(response, UserSignInResponse.class).getToken();
    }
}
