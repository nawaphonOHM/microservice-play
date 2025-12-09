package nawaphon.microservices.messaging.rest.utils;


import nawaphon.microservices.messaging.rest.pojo.Customer;
import org.springframework.core.ParameterizedTypeReference;

public class CustomerParameterizedTypeReference extends ParameterizedTypeReference<Customer> {

    public CustomerParameterizedTypeReference() {
        super();
    }
}
