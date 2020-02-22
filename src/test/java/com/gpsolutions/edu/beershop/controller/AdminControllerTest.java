package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.entity.BeerEntity;
import com.gpsolutions.edu.beershop.entity.OrderCompleteStatus;
import com.gpsolutions.edu.beershop.entity.OrderEntity;
import com.gpsolutions.edu.beershop.entity.OrderProcessStatus;
import com.gpsolutions.edu.beershop.repository.OrderRepository;
import com.gpsolutions.edu.beershop.security.Roles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        willReturn(createOrderEntityList()).given(orderRepository).
                findAllByOrderProcessStatusAndOrderCompleteStatus(OrderProcessStatus.READY,OrderCompleteStatus.NOT_COMPLETE);
        //when
        mockMvc.perform(get("/beer-shop-app/admin/orders").header("Authorization",token))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "      \"id\" : 1,\n" +
                        "      \"clientId\" : 1,\n" +
                        "      \"beerMap\" : {\n" +
                        "           \"BeerDTO(id=1, title=Goose, description=Strong, alco=5.7%, density=10%, price=5.0)\": 2," +
                        "           \"BeerDTO(id=1, title=Kozel, description=Strong, alco=5.7%, density=10%, price=5.0)\": 1" +
                        "       }," +
                        "      \"cost\" : 15\n" +
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
        willReturn(createOrderEntity()).given(orderRepository).findById(1l);
        //when
        mockMvc.perform(post("/beer-shop-app/admin/orders/1/complete-order").header("Authorization",token))
                .andExpect(status().isOk());
    }

    @Test
    public void testOrdersCompleteOrderWhenAccessedByClient() throws Exception {
        //given
        final String token = signInAsClient();
        willReturn(createOrderEntity()).given(orderRepository).findById(1l);
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

    private List<OrderEntity> createOrderEntityList(){
        final List<OrderEntity> orderEntityList = new ArrayList<>();
        orderEntityList.add(createOrderEntity().get());
        return orderEntityList;
    }

    private Optional<OrderEntity> createOrderEntity(){
        Optional<OrderEntity> orderEntity;
        orderEntity = Optional.of(new OrderEntity());
        orderEntity.get().setId(1l);
        orderEntity.get().setUserEntity(createUserInfo(Roles.CLIENT));
        orderEntity.get().setBeerList(createBeerList());
        orderEntity.get().setOrderCompleteStatus(OrderCompleteStatus.NOT_COMPLETE);
        orderEntity.get().setOrderProcessStatus(OrderProcessStatus.READY);
        return orderEntity;
    }

    private List<BeerEntity> createBeerList(){
        final List<BeerEntity> beerEntityList = new ArrayList<>();
        beerEntityList.add(createBeerEntity("Goose"));
        beerEntityList.add(createBeerEntity("Goose"));
        beerEntityList.add(createBeerEntity("Kozel"));
        return beerEntityList;
    }

    private BeerEntity createBeerEntity(final String title){
        final BeerEntity beerEntity = new BeerEntity();
        beerEntity.setId(1l);
        beerEntity.setTitle(title);
        beerEntity.setAlco("5.7%");
        beerEntity.setDensity("10%");
        beerEntity.setDescription("Strong");
        beerEntity.setPrice(5);
        return beerEntity;
    }
}
