package com.gpsolutions.edu.beershop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpsolutions.edu.beershop.dto.UserSignInResponse;
import com.gpsolutions.edu.beershop.entity.UserEntity;
import com.gpsolutions.edu.beershop.repository.UserRepository;
import com.gpsolutions.edu.beershop.security.Roles;
import lombok.extern.java.Log;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.gpsolutions.edu.beershop.security.Roles.CLIENT;
import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public abstract class AbstractServiceTest {


    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @MockBean
    protected UserRepository userRepository;

    protected String signInAsClient() throws Exception {

        final UserEntity user = createUserInfo(CLIENT);
        willReturn(Optional.of(user)).given(userRepository).findByLogin("vasya@email.com");

        final String response = mockMvc.perform(post("/beer-shop-app/client/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"login\" : \"vasya@email.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(144)))
                .andReturn().getResponse().getContentAsString();
        return "Bearer " + objectMapper.readValue(response, UserSignInResponse.class).getToken();
    }

    protected UserEntity createUserInfo(Roles role) {
        final UserEntity user = new UserEntity();
        user.setRole(role);
        user.setFio("Пупкин Василий Иванович");
        user.setPhoneNumber("+3752912345678");
        user.setInfo("Молодой инженер");
        user.setLogin("vasya@email.com");
        user.setPassword(passwordEncoder.encode("qwerty"));
        return user;
    }

}
