package nawaphon.microservices.reusable.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CustomerId(BigDecimal credit) {
    public CustomerId(@JsonProperty("credit") final BigDecimal credit) {
        this.credit = credit;
    }
}
