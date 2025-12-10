package nawaphon.microservices.messaging.grpc.client.pojo;

public record ResponseMessage<A>(Number code, String message, A results) {

}
