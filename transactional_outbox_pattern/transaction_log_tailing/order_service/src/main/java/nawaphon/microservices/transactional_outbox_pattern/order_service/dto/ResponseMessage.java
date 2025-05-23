package nawaphon.microservices.transactional_outbox_pattern.order_service.dto;

public record ResponseMessage<A>(Number code, String message, A results) {

}
