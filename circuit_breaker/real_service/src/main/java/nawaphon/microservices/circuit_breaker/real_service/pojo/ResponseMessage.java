package nawaphon.microservices.circuit_breaker.real_service.pojo;

public record ResponseMessage<A>(Number code, String message, A results) {

}
