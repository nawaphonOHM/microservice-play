package nawaphon.microservices.circuit_breaker.proxy.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import nawaphon.microservices.circuit_breaker.proxy.pojo.Message;
import nawaphon.microservices.circuit_breaker.proxy.pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/")
@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final RestTemplate restTemplate;

    private final String serviceIp;

    public MainController(RestTemplate restTemplate, @Value("${service-ip}") String serviceIp) {
        this.restTemplate = restTemplate;
        this.serviceIp = serviceIp;
    }


    @GetMapping("/call-service}")
    @CircuitBreaker(name = "call-service-breaker", fallbackMethod = "unavailable")
    public ResponseMessage<Message> getCustomer() {
        final String url = String.format("%s/first-get", serviceIp);
        final ResponseEntity<ResponseMessage<Message>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }
    
    
    private ResponseMessage<Message> unavailable()  {
        final Message message = new Message("Service is unavailable");
        
        
        return new ResponseMessage<>(
                HttpStatus.SERVICE_UNAVAILABLE.value(), 
                HttpStatus.SERVICE_UNAVAILABLE.toString(), 
                message);
    }
}
