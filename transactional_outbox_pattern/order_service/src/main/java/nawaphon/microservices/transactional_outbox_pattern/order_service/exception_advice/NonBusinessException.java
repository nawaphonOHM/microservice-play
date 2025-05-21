package nawaphon.microservices.transactional_outbox_pattern.order_service.exception_advice;

import nawaphon.microservices.transactional_outbox_pattern.order_service.ToHashMpModelException;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
}
