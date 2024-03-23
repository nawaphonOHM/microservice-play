package nawaphon.microservices.circuit_breaker.proxy.utils;


import nawaphon.microservices.messaging.rest.pojo.CustomerDetail;
import nawaphon.microservices.messaging.rest.pojo.ResponseMessage;
import org.springframework.core.ParameterizedTypeReference;

public class CustomerDetailsParameterizedTypeReference extends ParameterizedTypeReference<ResponseMessage<CustomerDetail>> {

    public CustomerDetailsParameterizedTypeReference() {
        super();
    }
}
