package nawaphon.microservices.server_side_discovery.service_a.exception_advisor;

import nawaphon.microservices.server_side_discovery.service_a.exception.UnknownHostRuntimeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceAGlobeException {



    @ExceptionHandler(UnknownHostRuntimeException.class)
    public String handleUnknownHostRuntimeException(final UnknownHostRuntimeException ex) {
        return ex.getMessage();
    }
}
