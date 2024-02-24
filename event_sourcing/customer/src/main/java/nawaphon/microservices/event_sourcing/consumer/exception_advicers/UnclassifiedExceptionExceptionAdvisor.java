package nawaphon.microservices.event_sourcing.consumer.exception_advicers;

import nawaphon.microservices.event_sourcing.consumer.exceptions.UnclassifiedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UnclassifiedExceptionExceptionAdvisor {

    @ExceptionHandler(value = UnclassifiedException.class)
    @ResponseBody
    public ResponseEntity<Object> handleException(final UnclassifiedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}