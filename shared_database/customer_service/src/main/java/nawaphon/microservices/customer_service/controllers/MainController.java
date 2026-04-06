package nawaphon.microservices.customer_service.controllers;

import nawaphon.microservices.reusable.entity.shared.Customer;
import nawaphon.microservices.reusable.pojo.CustomerId;
import nawaphon.microservices.customer_service.services.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/")
@ResponseBody
public class MainController {

    private final MainService mainService;

    public MainController(final MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping("/customer")
    public Customer postNewCustomer(@RequestBody final Customer newCustomer) {
        return mainService.addNewCustomer(newCustomer);
    }

    @GetMapping("/customer")
    public List<Customer> getCustomerByCriteria(@RequestParam final Map<String, String> params) {
        return mainService.getCustomerByCriteria(params);
    }

    @PatchMapping("/customer/{customer-uuid}/credit")
    public Customer patchCustomerCredit(@PathVariable("customer-uuid") final UUID customerUUID, @RequestBody final CustomerId credit) {
        return mainService.updateUserCredit(customerUUID, credit.credit());
    }

    @DeleteMapping("customer/{customer-uuid}")
    public UUID removeCustomer(@PathVariable("customer-uuid") final UUID customerUUID) {
        return mainService.removeCustomer(customerUUID);
    }
}
