package nawaphon.microservices.order_service.controllers;

import nawaphon.microservices.order_service.pojo.Order;
import nawaphon.microservices.order_service.pojo.OrderStatusEnvelop;
import nawaphon.microservices.order_service.pojo.ResponseMessage;
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
    public ResponseMessage<OrderStatusEnvelop> addOrders(@RequestBody final Order order) {
        final var results = mainService.addOrders(List.of(order));
        return new ResponseMessage<>(results.code(), results.message(), results.results().get(0));
    }
}
