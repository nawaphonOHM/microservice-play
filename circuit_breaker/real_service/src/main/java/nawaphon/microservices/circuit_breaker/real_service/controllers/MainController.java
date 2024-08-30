package nawaphon.microservices.circuit_breaker.real_service.controllers;

import nawaphon.microservices.circuit_breaker.real_service.pojo.Message;
import nawaphon.microservices.circuit_breaker.real_service.pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @GetMapping("/first-get")
    public ResponseMessage<Message> getCustomer() {
        final Message message = new Message("Call from real service. OK!");
        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), message);
    }

}
