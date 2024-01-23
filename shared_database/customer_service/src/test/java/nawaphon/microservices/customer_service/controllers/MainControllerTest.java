package nawaphon.microservices.customer_service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.services.MainService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
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
    void ableToRunAddCustomerPath() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();

        final Customer body = new Customer();

        body.setId(UUID.randomUUID());
        body.setCreditLimit(BigDecimal.ONE);

        BDDMockito.given(service.addNewCustomer(BDDMockito.refEq(body))).willReturn(
                new ResponseMessage<>(200, "Done", BDDMockito.refEq(body))
        );


        mvc.perform(
                MockMvcRequestBuilders.post("/post-customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(body))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        BDDMockito.verify(service, BDDMockito.times(1))
                .addNewCustomer(BDDMockito.refEq(body));
    }
}