package nawaphon.microservices.transactional_outbox_pattern.order_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import nawaphon.microservices.transactional_outbox_pattern.order_service.enums.OrderStatus;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
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

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "order_status", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private OrderStatus orderStatus;

    @Size(max = 255)
    @NotNull
    @Column(name = "order_name", nullable = false)
    private String orderName;

    @ColumnDefault("0.00")
    @Column(name = "price")
    private BigDecimal price;

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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @PrePersist
    public void prePersist() {
        this.orderId = UUID.randomUUID();
        this.orderStatus = OrderStatus.PENDING;
    }
}