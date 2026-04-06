package nawaphon.microservices.reusable.pojo;

public record ResponseMessage<A>(Number code, String message, A results) {

}
