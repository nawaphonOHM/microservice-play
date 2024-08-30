package nawaphon.microservices.customer_service.pojo;

public record ResponseMessage<A>(Number code, String message, A results) {

}
