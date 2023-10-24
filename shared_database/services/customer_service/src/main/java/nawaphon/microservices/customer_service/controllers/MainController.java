package nawaphon.microservices.customer_service.controllers;

import nawaphon.microservice.pojo.Customer;
import nawaphon.microservice.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.services.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/")
@ResponseBody
public class MainController {

    private final MainService mainService;

    public MainController(final MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping("/post-customer")
    public ResponseMessage<?> postNewCustomer(@RequestBody final Customer newCustomer) {
        return mainService.addNewCustomer(newCustomer);
    }

    @GetMapping("/get-customer-by-criteria")
    public ResponseMessage<?> getCustomerByCriteria(@RequestParam final Map<String, String> params) {
        return mainService.getCustomerByCriteria(params);
    }
}