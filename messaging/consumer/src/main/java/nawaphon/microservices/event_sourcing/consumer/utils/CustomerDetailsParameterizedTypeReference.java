package nawaphon.microservices.event_sourcing.consumer.utils;

import nawaphon.microservices.event_sourcing.consumer.pojo.CustomerDetail;
import nawaphon.microservices.event_sourcing.consumer.pojo.ResponseMessage;
import org.springframework.core.ParameterizedTypeReference;

public class CustomerDetailsParameterizedTypeReference extends ParameterizedTypeReference<ResponseMessage<CustomerDetail>> {

    public CustomerDetailsParameterizedTypeReference() {
        super();
    }
}
