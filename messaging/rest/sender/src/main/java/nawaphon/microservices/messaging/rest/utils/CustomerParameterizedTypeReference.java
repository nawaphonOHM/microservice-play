package nawaphon.microservices.messaging.rest.utils;


import nawaphon.microservices.messaging.rest.pojo.Customer;
import nawaphon.microservices.messaging.rest.pojo.ResponseMessage;
import org.springframework.core.ParameterizedTypeReference;

public class CustomerParameterizedTypeReference extends ParameterizedTypeReference<ResponseMessage<Customer>> {

    public CustomerParameterizedTypeReference() {
        super();
    }
}
