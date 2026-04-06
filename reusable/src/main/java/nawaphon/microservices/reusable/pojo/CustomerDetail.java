package nawaphon.microservices.reusable.pojo;

import java.util.UUID;

public record CustomerDetail(UUID customerId, String firstName, String lastName) {

}
