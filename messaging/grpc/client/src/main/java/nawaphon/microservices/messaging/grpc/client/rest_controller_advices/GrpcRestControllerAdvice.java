package nawaphon.microservices.messaging.grpc.client.rest_controller_advices;

import io.grpc.StatusException;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
class GrpcRestControllerAdvice {


    public void handleStatusException(StatusException exception) {

    }

}
