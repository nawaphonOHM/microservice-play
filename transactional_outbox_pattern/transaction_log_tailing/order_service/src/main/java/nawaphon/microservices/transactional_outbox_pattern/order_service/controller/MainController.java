package nawaphon.microservices.transactional_outbox_pattern.order_service.controller;

import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderId;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderSaveStatus;
import nawaphon.microservices.transactional_outbox_pattern.order_service.exception.SavingOrderUnsuccessfulException;
import nawaphon.microservices.transactional_outbox_pattern.order_service.service.MainService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping("/save-order")
    public OrderId saveOrder(@RequestBody @NonNull OrderRequest orderDetail) {

        final var saveInformation = mainService.saveOrder(orderDetail);

        final var done = saveInformation.saveStatus();

        if (!done) {
            throw new SavingOrderUnsuccessfulException();
        }

        return new OrderId(saveInformation.orderId());

    }

}
