package nawaphon.microservices.messaging.grpc.server.components;

import io.grpc.stub.StreamObserver;
import nawaphon.microservices.messaging.grpc.server.CustomerDetailMessage;
import nawaphon.microservices.messaging.grpc.server.CustomerMessage;
import nawaphon.microservices.messaging.grpc.server.MainServerGrpc;
import nawaphon.microservices.messaging.grpc.server.UUID;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;


@Component
class GrpcService extends MainServerGrpc.MainServerImplBase {

    private static final Logger log = LoggerFactory.getLogger(GrpcService.class);
    private final FakeDatabaseComponent fakeDatabaseComponent;

    GrpcService(FakeDatabaseComponent fakeDatabaseComponent) {
        this.fakeDatabaseComponent = fakeDatabaseComponent;
    }

    @Override
    public void customer(UUID request, @NonNull StreamObserver<CustomerMessage> responseObserver) {

        try {
            final var customer = fakeDatabaseComponent.getCustomers()
                    .stream().filter(it -> it.id().equals(java.util.UUID.fromString(request.getValue())))
                    .findFirst().orElseThrow();

            responseObserver.onNext(
                    CustomerMessage.newBuilder()
                            .setId(UUID.newBuilder().setValue(customer.id().toString()).build())
                            .setDetailsId(UUID.newBuilder().setValue(customer.detailsId().toString()).build())
                            .build()
            );

            responseObserver.onCompleted();


        } catch (NoSuchElementException e) {
            log.error("Cannot find customer with id: {}", request.getValue());
            responseObserver.onError(e);
        }

    }

    @Override
    public void customerDetail(UUID request, StreamObserver<CustomerDetailMessage> responseObserver) {
        super.customerDetail(request, responseObserver);
    }
}
