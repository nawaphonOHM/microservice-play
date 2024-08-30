package nawaphon.microservices.order_service.pojo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "\"ORDER\"")
public class Order {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId.toString() +
                ", status=" + status +
                ", total=" + total +
                '}';
    }
}
