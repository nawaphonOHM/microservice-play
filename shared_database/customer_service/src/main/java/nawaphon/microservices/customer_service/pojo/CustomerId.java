package nawaphon.microservices.customer_service.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CustomerId {
    private final BigDecimal credit;

    public CustomerId(@JsonProperty("credit") final BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getCredit() {
        return credit;
    }
}
