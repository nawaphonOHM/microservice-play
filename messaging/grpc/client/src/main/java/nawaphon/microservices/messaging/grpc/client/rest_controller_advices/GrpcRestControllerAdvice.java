package nawaphon.microservices.messaging.grpc.client.rest_controller_advices;

import io.grpc.StatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
class GrpcRestControllerAdvice {


    @ExceptionHandler(StatusException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleStatusException(StatusException exception) {

    }

}
