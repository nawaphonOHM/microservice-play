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
    private static final Logger logger = LogManager.getLogger(FakeDatabaseComponent.class);

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

        {
            customerFake = new Customer(UUID.randomUUID(), UUID.randomUUID());
            customerDetailFake = new CustomerDetail(customerFake.getDetailsId(), "John", "Doe");
            customers.add(customerFake);
            customerDetails.add(customerDetailFake);
            logger.info("Customer and CustomerDetail added: {}", customerFake);
        }

        {
            customerFake = new Customer(UUID.randomUUID(), UUID.randomUUID());
            customerDetailFake = new CustomerDetail(customerFake.getDetailsId(), "Jane", "Smith");
            customers.add(customerFake);
            customerDetails.add(customerDetailFake);
            logger.info("Customer and CustomerDetail added: {}", customerFake);
        }

        {
            customerFake = new Customer(UUID.randomUUID(), UUID.randomUUID());
            customerDetailFake = new CustomerDetail(customerFake.getDetailsId(), "Bob", "Johnson");
            customers.add(customerFake);
            customerDetails.add(customerDetailFake);
            logger.info("Customer and CustomerDetail added: {}", customerFake);
        }
    }
}
