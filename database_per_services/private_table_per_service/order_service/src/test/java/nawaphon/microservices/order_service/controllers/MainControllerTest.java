package nawaphon.microservices.order_service.controllers;

import nawaphon.microservices.order_service.pojo.Order;
import nawaphon.microservices.order_service.services.MainService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MainService service;

    @Test
    @Description("Test whether to call /hello-world")
    void ableToRequestGetRequest() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/hello-world")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ableToGetDataFromServiceTest() throws Exception {
        final Order order1 = new Order();

        order1.setStatus(true);
        order1.setCustomerId(BigInteger.ONE);
        order1.setTotal(new BigDecimal(1000));

        final List<Order> testData = List.of(order1);

        BDDMockito.given(service.firstService()).willReturn(testData);

        mvc.perform(MockMvcRequestBuilders.get("/hello-world").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].total", Matchers.is(1000)));
    }
}
