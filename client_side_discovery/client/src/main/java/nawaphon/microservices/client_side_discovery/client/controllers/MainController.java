package nawaphon.microservices.client_side_discovery.client.controllers;

import nawaphon.microservices.client_side_discovery.client.http_exchanges.ServiceAExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final ServiceAExchange serviceAExchange;


    public MainController(final ServiceAExchange serviceAExchange) {
        logger.info("MainController created");
        this.serviceAExchange = serviceAExchange;
    }

    @GetMapping("/hello-world")
    public String helloWorld() {
        return this.serviceAExchange.helloWorld();
    }
}
