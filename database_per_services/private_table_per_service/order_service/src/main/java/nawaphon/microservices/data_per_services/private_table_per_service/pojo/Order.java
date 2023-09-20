package nawaphon.microservices.data_per_services.private_table_per_service.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "\"ORDER\"")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "customer_id")
    private BigInteger customerId;

    @Column(name = "status")
    private boolean status;

    @Column(name = "total")
    private BigDecimal total;


    public UUID getId() {
        return id;
    }

    public Number getCustomerId() {
        return customerId;
    }

    public void setCustomerId(BigInteger customerId) {
        this.customerId = customerId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Number getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
