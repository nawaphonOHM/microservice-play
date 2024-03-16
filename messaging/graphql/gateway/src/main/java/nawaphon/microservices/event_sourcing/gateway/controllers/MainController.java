package nawaphon.microservices.event_sourcing.gateway.controllers;

import nawaphon.microservices.event_sourcing.gateway.components.FakeDatabaseComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final FakeDatabaseComponent fakeDatabaseComponent;

    public MainController(final FakeDatabaseComponent fakeDatabaseComponent) {
        this.fakeDatabaseComponent = fakeDatabaseComponent;
    }
}
