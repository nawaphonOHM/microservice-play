package nawaphon.microservices.event_sourcing.consumer.pojo;

import java.util.UUID;

public class CustomerDetail {

    private final UUID customerId;

    private final String firstName;

    private final String lastName;


    public CustomerDetail(final UUID customerId, final String firstName, final String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
