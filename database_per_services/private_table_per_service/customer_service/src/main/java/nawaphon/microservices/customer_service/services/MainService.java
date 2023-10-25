package nawaphon.microservices.customer_service.services;

import nawaphon.microservice.main.common.pojo.Customer;
import nawaphon.microservice.main.common.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    private final CustomerRepository customerRepository;

    public MainService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public ResponseMessage<List<Customer>> firstService() {
        final List<Customer> results = this.customerRepository.findAll();

        return new ResponseMessage<>(200, "Done", results);
    }
}
