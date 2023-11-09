package nawaphon.microservices.customer_service.exception_advices;

import nawaphon.microservices.customer_service.exceptions.UpdateNewCreditFailedException;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UpdateNewCreditFailedExceptionAdvice {

    @ExceptionHandler({UpdateNewCreditFailedException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage<String> buildMessage() {
        return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }
}
