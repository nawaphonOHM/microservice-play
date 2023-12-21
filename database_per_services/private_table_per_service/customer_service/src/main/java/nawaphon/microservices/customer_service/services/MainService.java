package nawaphon.microservices.customer_service.services;

import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;

import java.util.List;


public interface MainService {

    ResponseMessage<List<Customer>> firstService();
}
