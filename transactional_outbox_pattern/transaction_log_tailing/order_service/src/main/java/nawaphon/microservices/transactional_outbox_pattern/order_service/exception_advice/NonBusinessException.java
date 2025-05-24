package nawaphon.microservices.transactional_outbox_pattern.order_service.exception_advice;

import nawaphon.microservices.transactional_outbox_pattern.order_service.exception.SavingOrderUnsuccessfulException;
import nawaphon.microservices.transactional_outbox_pattern.order_service.exception.ToHashMpModelException;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NonBusinessException {

    @ExceptionHandler(ToHashMpModelException.class)
    public ResponseMessage<String> handleException(Exception ex) {

        return new ResponseMessage<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "There is something occur unexpectedly. Consult Software Developer to fix this",
                null
        );
    }

    @ExceptionHandler(SavingOrderUnsuccessfulException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage<String> buildMessage(final SavingOrderUnsuccessfulException exception) {
        return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage());
    }
}
