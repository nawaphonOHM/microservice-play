package nawaphon.microservices.messaging.rest.pojo;

public record ResponseMessage<A>(Number code, String message, A results) {

}
