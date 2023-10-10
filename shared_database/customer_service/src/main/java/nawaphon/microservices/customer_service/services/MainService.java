package nawaphon.microservices.customer_service.services;

import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Service
public class MainService {

    private static final Logger logger = LoggerFactory.getLogger(MainService.class);

    private final CustomerRepository customerRepository;

    public MainService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public ResponseMessage<?> getCustomerByCriteria(final Map<String, String> params) {
        final Customer probe = new Customer();

        params.forEach((key, value) -> {
            if (key.equalsIgnoreCase("id")) {
                logger.debug("There is id = {}", value);
                probe.setId(UUID.fromString(value));
            } else if (key.equalsIgnoreCase("creditlimit")) {
                logger.debug("There is creditLimit = {}", value);
                probe.setCreditLimit(new BigDecimal(value));
            }
        });

        return customerRepository.findBy(Example.of(probe), (query) -> new ResponseMessage<>(200, "Done", query.all()));

    }


    public ResponseMessage<?> addNewCustomer(final Customer newCustomer) {
        try {
            this.customerRepository.save(newCustomer);
        } catch (final Exception exception) {
            logger.error("There is an error while saving new customer on the database {0}", exception);
            return new ResponseMessage<>(500, "failed", "Data is fail to save on database");
        }

        logger.info("Saving new customer is done.");
        return new ResponseMessage<>(200, "Data is success to save on database", newCustomer);
    }
}
