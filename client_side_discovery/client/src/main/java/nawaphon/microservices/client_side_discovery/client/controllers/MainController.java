package nawaphon.microservices.client_side_discovery.client.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final RestTemplate restTemplate;


    public MainController(final RestTemplate restTemplate) {
        logger.info("MainController created");
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/hello-world", version = "1.0")
    public String helloWorld() {
        return this.restTemplate.getForObject("http://serviceA/client-side-discovery/service-a/1.0/hello-world", String.class);
    }
}
