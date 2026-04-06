package nawaphon.microservices.transactional_outbox_pattern.order_service.service;

import nawaphon.microservices.reusable.dto.OrderRequest;
import nawaphon.microservices.reusable.dto.OrderSaveStatus;
import org.jspecify.annotations.NonNull;

public interface MainService {
    OrderSaveStatus saveOrder(@NonNull final OrderRequest orderRequest);
}
