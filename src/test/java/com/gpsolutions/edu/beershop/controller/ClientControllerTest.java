package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.willReturn;


public class ClientControllerTest extends AbstractControllerTest {

    @MockBean
    protected OrderService orderService;

    @Test
    public void testClientMakeOrderIsOk() throws Exception {
        //given
        final String token = signInAsClient();

        //when
        mockMvc.perform(post("/beer-shop-app/orders/make-order").header("Authorization",token))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void testClientMakeOrderWhenNotAuthorized() throws Exception {
        //given
        //when
        mockMvc.perform(post("/beer-shop-app/orders/make-order")
                .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isForbidden());
    }

}
