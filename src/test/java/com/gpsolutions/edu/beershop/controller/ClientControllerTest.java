package com.gpsolutions.edu.beershop.controller;

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


public class ClientControllerTest extends AbstractControllerTest {

    @Test
    public void testClientMakeOrderIsOk() throws Exception {
        //given
        final String token = signInAsClient();
        //when
        mockMvc.perform(post("/beer-shop-app/client/make-order").header("Authorization",token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("clientId",1)
                            .content("[\n" +
                                    "  {\n" +
                                    "  \"beerId\" : 1,\n" +
                                    "  \"amount\" : 1\n" +
                                    "  },\n" +
                                    "  \n" +
                                    "  {\n" +
                                    "  \"beerId\" : 2,\n" +
                                    "  \"amount\" : 2\n" +
                                    "  }\n" +
                                    "]"))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void testClientMakeOrderWhenNotAuthorized() throws Exception {
        //given
        //when
        mockMvc.perform(post("/beer-shop-app/client/make-order")
                .contentType(MediaType.APPLICATION_JSON)
                .header("clientId",1)
                .content("[\n" +
                        "  {\n" +
                        "  \"beerId\" : 1,\n" +
                        "  \"amount\" : 1\n" +
                        "  },\n" +
                        "  \n" +
                        "  {\n" +
                        "  \"beerId\" : 2,\n" +
                        "  \"amount\" : 2\n" +
                        "  }\n" +
                        "]"))
                //then
                .andExpect(status().isForbidden());
    }


}
