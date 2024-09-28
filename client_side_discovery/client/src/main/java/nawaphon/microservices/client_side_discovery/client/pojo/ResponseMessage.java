package nawaphon.microservices.client_side_discovery.client.pojo;

public record ResponseMessage<A>(Number code, String message, A results) {

}
