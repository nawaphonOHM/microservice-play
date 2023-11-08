package nawaphon.microservices.order_service.controllers.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {


    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

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
}
