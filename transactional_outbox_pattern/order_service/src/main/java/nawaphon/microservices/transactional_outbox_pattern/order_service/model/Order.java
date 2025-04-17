package nawaphon.microservices.transactional_outbox_pattern.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Order entity representing a customer order.
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * Pre-persist hook to set default values before saving to the database.
     */
    @PrePersist
    public void prePersist() {
        if (orderNumber == null) {
            orderNumber = UUID.randomUUID().toString();
        }
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        if (status == null) {
            status = OrderStatus.CREATED;
        }
    }

    /**
     * Pre-update hook to update the updatedAt timestamp.
     */
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
