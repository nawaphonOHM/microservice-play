package nawaphon.microservices.order_service.controllers;

import nawaphon.microservice.main.common.pojo.Order;
import nawaphon.microservice.main.common.pojo.ResponseMessage;
import nawaphon.microservice.shared_database.common.pojo.OrderStatusEnvelop;
import nawaphon.microservices.order_service.services.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
@ResponseBody
public class MainController {

    private final MainService mainService;

    public MainController(final MainService mainService) {
        this.mainService = mainService;
    }


    @GetMapping("/get-order-by-criteria")
    public ResponseMessage<List<Order>> getOrderByCriteria(@RequestParam final Map<String, String> params) {
        return mainService.getOrderByCriteria(params);
    }

    @PostMapping("/add-orders")
    public ResponseMessage<List<OrderStatusEnvelop>> addOrders(@RequestBody final List<Order> orders) {
        return mainService.addOrders(orders);
    }
}