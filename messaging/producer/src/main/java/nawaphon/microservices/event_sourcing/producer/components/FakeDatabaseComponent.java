package nawaphon.microservices.event_sourcing.producer.components;

import nawaphon.microservices.event_sourcing.producer.pojo.Customer;
import nawaphon.microservices.event_sourcing.producer.pojo.CustomerDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FakeDatabaseComponent {

    private static final Logger LOGGER = LogManager.getLogger(FakeDatabaseComponent.class);

    private final List<Customer> customers = new ArrayList<>();
    private final List<CustomerDetail> customerDetails = new ArrayList<>();

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<CustomerDetail> getCustomerDetails() {
        return customerDetails;
    }

    @PostConstruct
    public void makeData() {
        Customer customerFake;
        CustomerDetail customerDetailFake;

        customerFake = new Customer(UUID.randomUUID());
        customers.add(customerFake);
        customerDetailFake = new CustomerDetail(customerFake.getId(), "John", "Doe");
        customerDetails.add(customerDetailFake);
        LOGGER.info("Added customer {} and detail {}", customerFake, customerDetailFake);

        customerFake = new Customer(UUID.randomUUID());
        customers.add(customerFake);
        customerDetailFake = new CustomerDetail(customerFake.getId(), "Jane", "Smith");
        customerDetails.add(customerDetailFake);
        LOGGER.info("Added customer {} and detail {}", customerFake, customerDetailFake);

        customerFake = new Customer(UUID.randomUUID());
        customers.add(customerFake);
        customerDetailFake = new CustomerDetail(customerFake.getId(), "Alice", "Johnson");
        customerDetails.add(customerDetailFake);
        LOGGER.info("Added customer {} and detail {}", customerFake, customerDetailFake);
    }
}
