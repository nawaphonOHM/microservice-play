package nawaphon.microservices.server_side_discovery.service_a.pojo;

public record ResponseMessage<A>(Number code, String message, A results) {

}
