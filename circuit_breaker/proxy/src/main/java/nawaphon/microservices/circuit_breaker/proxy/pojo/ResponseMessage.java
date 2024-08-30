package nawaphon.microservices.circuit_breaker.proxy.pojo;

public record ResponseMessage<A>(Number code, String message, A results) {

}
