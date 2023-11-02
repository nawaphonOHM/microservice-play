package nawaphon.microservice.shared_database.common.exception_advices;

import nawaphon.microservice.main.common.pojo.ResponseMessage;
import nawaphon.microservice.shared_database.common.exceptions.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerNotFoundExceptionAdvice {

    @ExceptionHandler({CustomerNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage<String> buildMessage(final CustomerNotFoundException exception) {
        return new ResponseMessage<>(HttpStatus.BAD_REQUEST.value(), exception.getStatusMessage(), exception.getDetailMessage());
    }
}
