package nawaphon.microservices.order_service.controllers;

import nawaphon.microservices.order_service.pojo.Order;
import nawaphon.microservices.order_service.services.MainService;
import nawaphon.microservices.order_service.pojo.ResponseMessage;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

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
    void ableToGetOrderByCriteria() throws Exception {
        final var body = new LinkedMultiValueMap<String, String>();

        body.add("id", "eeee");

        final var orders = List.<Order>of();

        BDDMockito.given(service.getOrderByCriteria(BDDMockito.argThat((var1) -> var1.containsKey("id") &&
                var1.get("id").equals("eeee")))).willReturn(
                new ResponseMessage<>(200, "Done", orders)
        );


        mvc.perform(
                MockMvcRequestBuilders.get("/get-order-by-criteria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(body)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        BDDMockito.verify(service, BDDMockito.times(1))
                .getOrderByCriteria(BDDMockito.argThat((var1) -> var1.containsKey("id") &&
                        var1.get("id").equals("eeee")));

    }

}