package nawaphon.microservices.client_side_discovery.service_a.controllers;

import nawaphon.microservices.client_side_discovery.service_a.exception.UnknownHostRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @GetMapping("/hello-world")
    public String home() {
        final String hostname;

        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (final UnknownHostException e) {
            logger.error(e.getMessage(), e);
            throw new UnknownHostRuntimeException();
        }
        return String.format("Hello world this request was processed by %s", hostname);
    }
}
