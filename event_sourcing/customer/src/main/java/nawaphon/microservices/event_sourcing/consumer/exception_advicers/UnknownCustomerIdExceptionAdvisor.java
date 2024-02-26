package nawaphon.microservices.event_sourcing.consumer.exception_advicers;

import nawaphon.microservices.event_sourcing.consumer.exceptions.UnknownCustomerIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UnknownCustomerIdExceptionAdvisor {

    @ExceptionHandler(value = UnknownCustomerIdException.class)
    @ResponseBody
    public ResponseEntity<Object> handleException(final UnknownCustomerIdException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
