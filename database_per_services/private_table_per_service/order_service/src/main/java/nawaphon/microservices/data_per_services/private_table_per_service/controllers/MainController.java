package nawaphon.microservices.data_per_services.private_table_per_service.controllers;

import nawaphon.microservices.data_per_services.private_table_per_service.pojo.Order;
import nawaphon.microservices.data_per_services.private_table_per_service.pojo.ResponseMessage;
import nawaphon.microservices.data_per_services.private_table_per_service.services.MainService;
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