package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.security.Roles;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import static org.mockito.BDDMockito.willReturn;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasLength;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerTest extends AbstractControllerTest {

    @Test
    public void testClientSignInIsOk() throws Exception {
        // given
        signInAsClient();
        // when
        mockMvc.perform(post("/beer-shop-app/client/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"login\" : \"vasya@email.com\",\n" +
                        "   \"password\" : \"qwerty\"\n" +
                        "}"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token",hasLength(144)));
    }

    @Test
    public void testClientSignUpIsCreated() throws Exception {
        // given
        willReturn(Optional.empty(), Optional.of(createUserInfo(Roles.CLIENT))).given(userRepository)
                .findByLogin("vasya@email.com");
        // when
        mockMvc.perform(post("/beer-shop-app/client/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"login\" : \"vasya@email.com\",\n" +
                        "  \"password\" : \"qwerty\",\n" +
                        "  \"fio\" : \"Пупкин Василий Иванович\",\n" +
                        "  \"phoneNumber\" : \"+3752912345678\",\n" +
                        "  \"info\" : \"some info\" \n" +
                        "}"))
                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("token",hasLength(144)));
    }

    @Test
    public void testStudentSignUpWhenUserAlreadyExisted() throws Exception {
        // given
        signInAsClient();
        // when
        mockMvc.perform(post("/beer-shop-app/client/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"login\" : \"vasya@email.com\",\n" +
                        "  \"password\" : \"qwerty\",\n" +
                        "  \"fio\" : \"Пупкин Василий Иванович\",\n" +
                        "  \"phoneNumber\" : \"+3752912345678\",\n" +
                        "  \"info\" : \"Молодой инженер\" \n" +
                        "}"))
                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testStudentSignInWithWrongPassword() throws Exception {
        // given
        signInAsClient();
        // when
        mockMvc.perform(post("/beer-shop-app/client/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"vasya@email.com\",\n" +
                        "  \"password\" : \"qerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testStudentSignInWithWrongEmail() throws Exception {
        // given
        signInAsClient();
        // when
        mockMvc.perform(post("/beer-shop-app/client/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"neVasya@email.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }
}
