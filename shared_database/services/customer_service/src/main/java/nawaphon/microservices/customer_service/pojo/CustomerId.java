package nawaphon.microservices.customer_service.pojo;

import java.math.BigDecimal;

public class CustomerId {
    private final BigDecimal credit;

    CustomerId(final BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getCredit() {
        return credit;
    }
}
