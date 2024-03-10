package nawaphon.microservices.event_sourcing.consumer.utils;

import nawaphon.microservices.event_sourcing.consumer.pojo.Customer;
import nawaphon.microservices.event_sourcing.consumer.pojo.ResponseMessage;
import org.springframework.core.ParameterizedTypeReference;

public class CustomerParameterizedTypeReference extends ParameterizedTypeReference<ResponseMessage<Customer>> {
}
