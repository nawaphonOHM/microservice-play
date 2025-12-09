package nawaphon.microservices.circuit_breaker.real_service.controllers;

import nawaphon.microservices.circuit_breaker.real_service.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @GetMapping("/first-get")
    public Message getCustomer() {

        return new Message("Call from real service. OK!");
    }

}
