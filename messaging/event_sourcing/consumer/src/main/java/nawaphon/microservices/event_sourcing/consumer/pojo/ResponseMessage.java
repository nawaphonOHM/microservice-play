package nawaphon.microservices.event_sourcing.consumer.pojo;

public record ResponseMessage<A>(Number code, String message, A results) {

}
