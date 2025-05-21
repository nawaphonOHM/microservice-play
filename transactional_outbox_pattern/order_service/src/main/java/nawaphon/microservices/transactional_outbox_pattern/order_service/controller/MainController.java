package nawaphon.microservices.transactional_outbox_pattern.order_service.controller;

import nawaphon.microservices.transactional_outbox_pattern.order_service.dto.OrderRequest;
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
    public ResponseMessage<String> saveOrder(@RequestBody @NonNull OrderRequest orderDetail) {

        final boolean done = mainService.saveOrder(orderDetail);

        if (done) {
            return new ResponseMessage<>(HttpStatus.OK.value(), "OK", "Order saved successfully");
        } else {
            return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "Order not saved");
        }

    }

}
