package nawaphon.microservices.transactional_outbox_pattern.order_service.service;

import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderSaveStatus;
import org.springframework.lang.NonNull;

public interface MainService {
    OrderSaveStatus saveOrder(@NonNull final OrderRequest orderRequest);
}
