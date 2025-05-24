package nawaphon.microservices.messaging.rest.utils;


import nawaphon.microservices.messaging.rest.pojo.CustomerDetail;
import nawaphon.microservices.messaging.rest.pojo.ResponseMessage;
import org.springframework.core.ParameterizedTypeReference;

public class CustomerDetailsParameterizedTypeReference extends ParameterizedTypeReference<CustomerDetail> {

    public CustomerDetailsParameterizedTypeReference() {
        super();
    }
}
