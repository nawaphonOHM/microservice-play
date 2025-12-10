package nawaphon.microservices.customer_service.controllers;

import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.services.MainService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private MainService service;

    public MainControllerTest() {
    }

    @Test
    @Description("Test whether to call /hello-world")
    public void ableToRequestGetRequest() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/hello-world")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ableToGetDataFromServiceTest() throws Exception {
        final var uuidGenTest = UUID.randomUUID();
        final var customer1 = new Customer();

        customer1.setCreditLimit(new BigDecimal(1000));
        customer1.setId(uuidGenTest);

        final var testData = List.of(customer1);

        BDDMockito.given(service.firstService()).willReturn(testData);

        mvc.perform(MockMvcRequestBuilders.get("/hello-world").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].creditLimit", Matchers.is(1000)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(uuidGenTest.toString())));
    }
}
