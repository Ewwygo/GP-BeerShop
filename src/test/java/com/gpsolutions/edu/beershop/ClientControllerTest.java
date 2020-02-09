package com.gpsolutions.edu.beershop;

import org.hibernate.validator.internal.metadata.aggregated.MetaDataBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        mockMvc.perform(post("/beer-shop-app/client/sign-up")
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

    @Test
    public void testClientSignInIsOk() throws Exception {

        mockMvc.perform(post("/beer-shop-app/client/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "   \"email\" : \"vasya@email.com\",\n" +
                                    "   \"password\" : \"qwerty\"\n" +
                                    "}"))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void testClientAddToBucketIsOk() throws Exception {

        mockMvc.perform(post("/beer-shop-app/client/add-to-bucket/catalog/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("clientId",1))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void testClientMakeOrderIsOk() throws Exception {
        mockMvc.perform(post("/beer-shop-app/client/make-order")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("clientId",1))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void testClientRemoveFromBucketIsOk() throws Exception {
        mockMvc.perform(post("/beer-shop-app/client/bucket/remove-from-bucket/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("clientId",1))
                .andExpect(status().isOk());
    }

}
