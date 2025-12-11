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

            log.info("Found customer with id: {}", customer.id());

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
    public void customerDetail(UUID request, @NonNull StreamObserver<CustomerDetailMessage> responseObserver) {

        try {
            final var customerDetails = fakeDatabaseComponent.getCustomerDetails()
                    .stream().filter(it -> it
                            .customerId().equals(java.util.UUID.fromString(request.getValue())))
                    .findFirst().orElseThrow();

            log.info("Found customer detail with id: {}", customerDetails.customerId());

            responseObserver.onNext(CustomerDetailMessage.newBuilder()
                    .setCustomerId(UUID.newBuilder().setValue(customerDetails.customerId().toString()).build())
                    .setFirstName(customerDetails.firstName())
                    .setLastName(customerDetails.lastName())
                    .build());

            responseObserver.onCompleted();
        } catch (NoSuchElementException e) {
            log.error("Cannot find customer detail with id: {}", request.getValue());
            responseObserver.onError(e);
        }

    }
}
