package nawaphon.microservices.order_service.services;


import nawaphon.microservices.order_service.pojo.Order;
import nawaphon.microservices.order_service.pojo.ResponseMessage;

import java.util.List;

public interface MainService {

    List<Order> firstService();
}
