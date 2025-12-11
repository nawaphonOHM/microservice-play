package nawaphon.microservices.messaging.grpc.client.rest_controller_advices;

import io.grpc.StatusException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
class GrpcRestControllerAdvice {


    @ExceptionHandler(StatusException.class)
    public void handleStatusException(StatusException exception) {

    }

}
