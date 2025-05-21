package nawaphon.microservices.transactional_outbox_pattern.order_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "orders", uniqueConstraints = {
        @UniqueConstraint(name = "orders_pk_2", columnNames = {"order_id"})
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Size(max = 255)
    @NotNull
    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}