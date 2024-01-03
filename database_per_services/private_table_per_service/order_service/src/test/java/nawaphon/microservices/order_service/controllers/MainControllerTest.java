package nawaphon.microservices.order_service.controllers;

import nawaphon.microservices.order_service.pojo.Order;
import nawaphon.microservices.order_service.pojo.ResponseMessage;
import nawaphon.microservices.order_service.services.MainService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigInteger;
import java.util.List;

@WebMvcTest(MainController.class)
public class MainControllerTest {

    private final MockMvc mvc;

    @MockBean
    private final MainService service;

    @Autowired
    public MainControllerTest(final MockMvc mvc, final MainService service) {
        this.mvc = mvc;
        this.service = service;
    }

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

        final List<Order> testData = List.of(order1);
        final ResponseMessage<List<Order>> responseMessage = new ResponseMessage<>(
                HttpStatus.OK.value(), HttpStatus.OK.name(), testData);

        BDDMockito.given(service.firstService()).willReturn(responseMessage);

        mvc.perform(MockMvcRequestBuilders.get("/hello-world").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.OK.name())));
    }
}