package nawaphon.microservices.order_service.exception_advices;

import nawaphon.microservice.main.common.pojo.ResponseMessage;
import nawaphon.microservices.order_service.exceptions.InsufficientException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InsufficientExceptionAdvice {

    @ExceptionHandler({InsufficientException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage<String> buildMessage(final InsufficientException exception) {
        return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getStatusMessage(), exception.getDetailMessage());
    }
}
