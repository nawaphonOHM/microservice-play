package nawaphon.microservices.customer_service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.pojo.CustomerId;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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

    @Test
    void ableToGetCustomerByCriteria() throws Exception {
        final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("id", "eeee");

        final List<Customer> customers = List.of();


        BDDMockito.given(service.getCustomerByCriteria(BDDMockito.argThat((var1) -> var1.containsKey("id") &&
                var1.get("id").equals("eeee")))).willReturn(
                new ResponseMessage<>(200, "Done", BDDMockito.refEq(customers))
        );

        mvc.perform(
                MockMvcRequestBuilders.get("/get-customer-by-criteria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(body)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        BDDMockito.verify(service, BDDMockito.times(1))
                .getCustomerByCriteria(BDDMockito.argThat((var1) -> var1.containsKey("id") &&
                        var1.get("id").equals("eeee")));
    }

    @Test
    void ableToUpdateCustomerCredit() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();

        final UUID id = UUID.randomUUID();

        final CustomerId body = new CustomerId(BigDecimal.TEN);

        final Customer response = new Customer();

        response.setId(id);
        response.setCreditLimit(BigDecimal.TEN);


        BDDMockito.given(service.updateUserCredit(BDDMockito.refEq(id), BDDMockito.refEq(body.getCredit())))
                .willReturn(
                        new ResponseMessage<>(200, "Done", response)
                );

        mvc.perform(
                MockMvcRequestBuilders.patch("/update-customer-credit/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(body))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        BDDMockito.verify(service, BDDMockito.times(1))
                .updateUserCredit(BDDMockito.refEq(id), BDDMockito.refEq(body.getCredit()));
    }
}