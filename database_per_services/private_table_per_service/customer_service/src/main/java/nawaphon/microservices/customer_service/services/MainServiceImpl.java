package nawaphon.microservices.customer_service.services;

import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    private final CustomerRepository customerRepository;

    public MainServiceImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public ResponseMessage<List<Customer>> firstService() {
        final List<Customer> results = this.customerRepository.findAll();

        return new ResponseMessage<>(200, "Done", results);
    }
}
