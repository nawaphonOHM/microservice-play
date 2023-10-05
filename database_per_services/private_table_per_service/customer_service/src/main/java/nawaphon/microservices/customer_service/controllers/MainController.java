package nawaphon.microservices.customer_service.controllers;

import nawaphon.microservices.customer_service.pojo.Order;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.services.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@ResponseBody
public class MainController {

    private final MainService mainService;

    public MainController(final MainService mainService) {
        this.mainService = mainService;
    }


    @GetMapping("/hello-world")
    public ResponseMessage<Order> firstGetMethod(){
        return mainService.firstService();
    }
}