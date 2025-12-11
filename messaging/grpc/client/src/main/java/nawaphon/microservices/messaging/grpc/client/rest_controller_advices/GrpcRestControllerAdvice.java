package nawaphon.microservices.messaging.grpc.client.rest_controller_advices;

import io.grpc.StatusException;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
class GrpcRestControllerAdvice {


    private static final Logger log = LoggerFactory.getLogger(GrpcRestControllerAdvice.class);

    @ExceptionHandler(StatusException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleStatusException(@NonNull StatusException exception) {
        log.error("gRPC error: {}", exception.getMessage());
    }

}
