package nawaphon.microservices.self_registration.service_a.pojo;

public record ResponseMessage<A>(Number code, String message, A results) {

}
