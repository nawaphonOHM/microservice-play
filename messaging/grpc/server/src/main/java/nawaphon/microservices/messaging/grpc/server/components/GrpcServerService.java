package nawaphon.microservices.messaging.grpc.server.components;

import io.grpc.stub.StreamObserver;
import nawaphon.microservices.messaging.grpc.server.*;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

import java.util.NoSuchElementException;


@GrpcService
class GrpcServerService extends MainServerServiceGrpc.MainServerServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(GrpcServerService.class);
    private final FakeDatabaseComponent fakeDatabaseComponent;

    GrpcServerService(FakeDatabaseComponent fakeDatabaseComponent) {
        this.fakeDatabaseComponent = fakeDatabaseComponent;
    }

    @Override
    public void customer(GetCustomerByCustomerUUIDRequest request, @NonNull StreamObserver<GetCustomerByCustomerUUIDResponse> responseObserver) {

        try {
            final var customer = fakeDatabaseComponent.getCustomers()
                    .stream().filter(it -> it.id().equals(java.util.UUID.fromString(request.getUuid())))
                    .findFirst().orElseThrow();

            log.info("Found customer with id: {}", customer.id());

            responseObserver.onNext(
                    GetCustomerByCustomerUUIDResponse.newBuilder()
                            .setCustomerDetailUUID(customer.id().toString())
                            .setCustomerDetailUUID(customer.id().toString())
                            .build()
            );

            responseObserver.onCompleted();


        } catch (NoSuchElementException e) {
            log.error("Cannot find customer with id: {}", request.getUuid());
            responseObserver.onError(e);
        }

    }

    @Override
    public void customerDetail(GetCustomerDetailByCustomerUUIDRequest request, @NonNull StreamObserver<GetCustomerDetailByCustomerUUIDResponse> responseObserver) {

        try {
            final var customerDetails = fakeDatabaseComponent.getCustomerDetails()
                    .stream().filter(it -> it
                            .customerId().equals(java.util.UUID.fromString(request.getUuid())))
                    .findFirst().orElseThrow();

            log.info("Found customer detail with id: {}", customerDetails.customerId());

            responseObserver.onNext(GetCustomerDetailByCustomerUUIDResponse.newBuilder()
                    .setCustomerUUID(customerDetails.customerId().toString()).setFirstName(customerDetails.firstName())
                    .setLastName(customerDetails.lastName()).build())
            ;

            responseObserver.onCompleted();
        } catch (NoSuchElementException e) {
            log.error("Cannot find customer detail with id: {}", request.getUuid());
            responseObserver.onError(e);
        }

    }
}
