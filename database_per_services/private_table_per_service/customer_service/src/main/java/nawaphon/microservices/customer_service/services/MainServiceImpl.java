package nawaphon.microservices.customer_service.services;

import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    private final CustomerRepository customerRepository;

    public MainServiceImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> firstService() {
        return this.customerRepository.findAll();
    }
}
