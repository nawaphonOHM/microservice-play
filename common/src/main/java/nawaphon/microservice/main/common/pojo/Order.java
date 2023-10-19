package nawaphon.microservice.main.common.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "\"ORDER\"")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "customer_id")
    @ManyToOne()
    @JoinColumn(name = "id")
    private Customer customerId;

    @Column(name = "status")
    private boolean status;

    @Column(name = "total")
    private BigDecimal total;


    public UUID getId() {
        return id;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomerId() {
        return customerId;
    }
}
