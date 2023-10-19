package nawaphon.microservices.order_service.controllers;

import nawaphon.microservice.pojo.Order;
import nawaphon.microservice.pojo.ResponseMessage;
import nawaphon.microservices.order_service.services.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
@ResponseBody
public class MainController {

    private final MainService mainService;

    public MainController(final MainService mainService) {
        this.mainService = mainService;
    }


    @GetMapping("/hello-world")
    public ResponseMessage<List<Order>> firstGetMethod() {
        return mainService.firstService();
    }
}