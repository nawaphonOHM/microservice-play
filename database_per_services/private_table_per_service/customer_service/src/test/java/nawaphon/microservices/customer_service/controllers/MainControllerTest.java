package nawaphon.microservices.customer_service.controllers;

import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.services.MainService;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


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
    public void ableToRequestGetRequest() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/hello-world")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ableToGetDataFromServiceTest() throws Exception {
        final UUID uuidGenTest = UUID.randomUUID();
        final Customer customer1 = new Customer();

        customer1.setCreditLimit(new BigDecimal(1000));
        customer1.setId(uuidGenTest);

        final List<Customer> testData = List.of(customer1);
        final ResponseMessage<List<Customer>> responseMessage = new ResponseMessage<>(
                HttpStatus.OK.value(), HttpStatus.OK.name(), testData);

        BDDMockito.given(service.firstService()).willReturn(responseMessage);

        mvc.perform(MockMvcRequestBuilders.get("/hello-world").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.OK.name())));
    }
}