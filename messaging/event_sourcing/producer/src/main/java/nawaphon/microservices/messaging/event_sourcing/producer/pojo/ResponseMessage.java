package nawaphon.microservices.messaging.event_sourcing.producer.pojo;

public record ResponseMessage<A>(Number code, String message, A results) {

}
