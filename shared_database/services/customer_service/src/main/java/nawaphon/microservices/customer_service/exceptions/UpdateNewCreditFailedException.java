package nawaphon.microservices.customer_service.exceptions;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateNewCreditFailedException extends RuntimeException {

    private final BigDecimal credit;
    private final UUID customerId;

    public UpdateNewCreditFailedException(final BigDecimal credit, final UUID customerId) {
        this.credit = credit;
        this.customerId = customerId;
    }


    public String getStatusMessage() {
        return "failed";
    }

    public String getDetailMessage() {
        return "customerId" + " = " + customerId + " with credit " + credit + " is failed to update";
    }
}
