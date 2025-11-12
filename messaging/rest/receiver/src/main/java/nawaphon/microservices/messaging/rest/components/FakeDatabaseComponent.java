package nawaphon.microservices.messaging.rest.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import nawaphon.microservices.messaging.rest.pojo.Customer;
import nawaphon.microservices.messaging.rest.pojo.CustomerDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FakeDatabaseComponent {
    private static final Logger logger = LogManager.getLogger(FakeDatabaseComponent.class);

    private final List<Customer> customers = new ArrayList<>();
    private final List<CustomerDetail> customerDetails = new ArrayList<>();

    private final ObjectMapper objectMapper;

    public FakeDatabaseComponent(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<CustomerDetail> getCustomerDetails() {
        return customerDetails;
    }

    @PostConstruct
    public void makeData() {
        {
            final var customerFake = new Customer(UUID.randomUUID(), UUID.randomUUID());
            final var customerDetailFake = new CustomerDetail(customerFake.detailsId(), "John", "Doe");
            customers.add(customerFake);
            customerDetails.add(customerDetailFake);
            try {
                logger.info("Customer and CustomerDetail added: {}", objectMapper.writeValueAsString(customerFake));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Unable to serialize value: ", e);
            }
        }

        {
            final var customerFake = new Customer(UUID.randomUUID(), UUID.randomUUID());
            final var customerDetailFake = new CustomerDetail(customerFake.detailsId(), "Jane", "Smith");
            customers.add(customerFake);
            customerDetails.add(customerDetailFake);
            try {
                logger.info("Customer and CustomerDetail added: {}", objectMapper.writeValueAsString(customerFake));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Unable to serialize value: ", e);
            }
        }

        {
            final var customerFake = new Customer(UUID.randomUUID(), UUID.randomUUID());
            final var customerDetailFake = new CustomerDetail(customerFake.detailsId(), "Bob", "Johnson");
            customers.add(customerFake);
            customerDetails.add(customerDetailFake);
            try {
                logger.info("Customer and CustomerDetail added: {}", objectMapper.writeValueAsString(customerFake));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Unable to serialize value:", e);
            }
        }
    }
}
