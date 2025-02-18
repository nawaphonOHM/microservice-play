package nawaphon.microservices.server_side_discovery.service_a.controllers;

import nawaphon.microservices.server_side_discovery.service_a.pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @GetMapping("/hello-world")
    public ResponseMessage<String> home() {
        final String hostname;

        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (final UnknownHostException e) {
            logger.error(e.getMessage(), e);
            return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Unable to complete request");
        }
        return new ResponseMessage<>(HttpStatus.OK.value(),
                HttpStatus.OK.toString(),
                String.format("Hello world this request was processed by %s", hostname));
    }
}
