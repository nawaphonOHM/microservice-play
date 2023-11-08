package nawaphon.microservices.customer_service.exception_advices;

import nawaphon.microservices.customer_service.exceptions.FailToSaveCustomerException;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FailToSaveCustomerExceptionAdvice {


    @ExceptionHandler({FailToSaveCustomerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage<String> buildMessage(final FailToSaveCustomerException exception) {
        return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getStatusMessage(), exception.getDetailMessage());
    }
}
