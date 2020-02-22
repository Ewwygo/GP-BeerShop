package com.gpsolutions.edu.beershop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpsolutions.edu.beershop.dto.UserSignInResponse;
import com.gpsolutions.edu.beershop.entity.AuthInfoEntity;
import com.gpsolutions.edu.beershop.entity.UserEntity;
import com.gpsolutions.edu.beershop.repository.AuthInfoRepository;
import com.gpsolutions.edu.beershop.repository.UserRepository;
import com.gpsolutions.edu.beershop.security.LoadClientDetailService;
import com.gpsolutions.edu.beershop.security.Roles;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

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

    @MockBean
    protected AuthInfoRepository authInfoRepository;
    @MockBean
    protected UserRepository userRepository;

    protected String signInAsClient() throws Exception {

        final AuthInfoEntity authInfo = createAuthInfo(CLIENT);
        willReturn(Optional.of(authInfo)).given(authInfoRepository).findByLogin("vasya@email.com");

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

        final AuthInfoEntity authInfo = createAuthInfo(ADMIN);
        willReturn(Optional.of(authInfo)).given(authInfoRepository).findByLogin("admin@email.com");

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

    protected AuthInfoEntity createAuthInfo(Roles role) {
        final UserEntity user = new UserEntity();
        user.setRole(role);
        user.setEmail("vasya@email.com");

        final AuthInfoEntity authInfo = new AuthInfoEntity();
        authInfo.setLogin(user.getEmail());
        authInfo.setPassword(passwordEncoder.encode("qwerty"));
        authInfo.setUser(user);
        return authInfo;
    }


}
