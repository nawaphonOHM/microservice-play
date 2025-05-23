package nawaphon.microservices.transactional_outbox_pattern.order_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "order_outbox")
public class OrderOutbox {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 255)
    @NotNull
    @Column(name = "aggregatetype", nullable = false)
    private String aggregatetype;

    @Size(max = 255)
    @NotNull
    @Column(name = "aggregateid", nullable = false)
    private String aggregateid;

    @Size(max = 255)
    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "payload", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> payload;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAggregatetype() {
        return aggregatetype;
    }

    public void setAggregatetype(String aggregatetype) {
        this.aggregatetype = aggregatetype;
    }

    public String getAggregateid() {
        return aggregateid;
    }

    public void setAggregateid(String aggregateid) {
        this.aggregateid = aggregateid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    @PrePersist
    public void prePersist() {
        aggregateid = UUID.randomUUID().toString();
    }
}