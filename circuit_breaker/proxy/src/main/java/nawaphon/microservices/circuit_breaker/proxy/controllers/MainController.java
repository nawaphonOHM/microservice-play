package nawaphon.microservices.circuit_breaker.proxy.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import nawaphon.microservices.circuit_breaker.proxy.http_exchanges.RealServiceExchange;
import nawaphon.microservices.circuit_breaker.proxy.pojo.Message;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final RealServiceExchange realServiceExchange;

    public MainController(RealServiceExchange realServiceExchange) {
        this.realServiceExchange = realServiceExchange;
    }


    @GetMapping("/call-service")
    @CircuitBreaker(name = "call-service-breaker", fallbackMethod = "unavailable")
    public Message getCustomer() {
        final var responseEntity = realServiceExchange.getCustomer();

        logger.info("response: {}", responseEntity);


        return responseEntity;
    }
    
    
    @Contract("_ -> new")
    private @NonNull Message unavailable(final Exception exception)  {

        logger.error("Call service is unavailable", exception);

        return new Message("Service is unavailable");
    }
}
