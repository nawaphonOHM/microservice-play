package nawaphon.microservices.messaging.grpc.server.components;

import io.grpc.stub.StreamObserver;
import nawaphon.microservices.messaging.grpc.server.CustomerDetailMessage;
import nawaphon.microservices.messaging.grpc.server.CustomerMessage;
import nawaphon.microservices.messaging.grpc.server.MainServerGrpc;
import nawaphon.microservices.messaging.grpc.server.UUID;
import org.springframework.stereotype.Component;


@Component
class GrpcService extends MainServerGrpc.MainServerImplBase {
    @Override
    public void customer(UUID request, StreamObserver<CustomerMessage> responseObserver) {
        super.customer(request, responseObserver);
    }

    @Override
    public void customerDetail(UUID request, StreamObserver<CustomerDetailMessage> responseObserver) {
        super.customerDetail(request, responseObserver);
    }
}
