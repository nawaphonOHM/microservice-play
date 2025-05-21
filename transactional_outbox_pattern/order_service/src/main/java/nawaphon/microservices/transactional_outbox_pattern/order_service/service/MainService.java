package nawaphon.microservices.transactional_outbox_pattern.order_service.service;

import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import org.springframework.lang.NonNull;

public interface MainService {
    boolean saveOrder(@NonNull final OrderRequest orderRequest);
}
