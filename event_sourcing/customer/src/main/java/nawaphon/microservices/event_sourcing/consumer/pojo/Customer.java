package nawaphon.microservices.event_sourcing.consumer.pojo;

import java.math.BigDecimal;
import java.util.UUID;

public class Customer {

    private UUID id;

    private BigDecimal creditLimit;

    private Status status;

    public Customer() {
        this.creditLimit = BigDecimal.ZERO;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", creditLimit=" + creditLimit +
                ", status=" + status +
                '}';
    }

    public enum Status {
        ACTIVATE,
        DEACTIVATE
    }
}
