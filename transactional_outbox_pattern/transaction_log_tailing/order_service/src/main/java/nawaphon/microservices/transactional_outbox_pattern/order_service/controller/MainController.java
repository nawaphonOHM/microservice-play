package nawaphon.microservices.transactional_outbox_pattern.order_service.controller;

import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderId;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderSaveStatus;
import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.ResponseMessage;
import nawaphon.microservices.transactional_outbox_pattern.order_service.service.MainService;
import org.springframework.http.HttpStatus;
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
    public ResponseMessage<OrderId> saveOrder(@RequestBody @NonNull OrderRequest orderDetail) {

        final OrderSaveStatus saveInformation = mainService.saveOrder(orderDetail);

        final boolean done = saveInformation.saveStatus();

        if (done) {
            return new ResponseMessage<>(HttpStatus.OK.value(), "OK", new OrderId(saveInformation.orderId()));
        } else {
            return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", new OrderId(null));
        }

    }

}
