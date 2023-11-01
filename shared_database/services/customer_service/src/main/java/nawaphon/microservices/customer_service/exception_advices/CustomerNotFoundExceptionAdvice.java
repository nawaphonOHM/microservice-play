package nawaphon.microservices.customer_service.exception_advices;

import nawaphon.microservice.main.common.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.exceptions.CustomerNotFoundException;
import nawaphon.microservices.customer_service.exceptions.FailToSaveCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerNotFoundExceptionAdvice {

    @ExceptionHandler({CustomerNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage<String> buildMessage(final FailToSaveCustomerException exception) {
        return new ResponseMessage<>(HttpStatus.BAD_REQUEST.value(), exception.getStatusMessage(), exception.getDetailMessage());
    }
}
