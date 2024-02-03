package nawaphon.microservices.customer_service.services;

import nawaphon.microservices.customer_service.exceptions.CustomerNotFoundException;
import nawaphon.microservices.customer_service.exceptions.FailToSaveCustomerException;
import nawaphon.microservices.customer_service.exceptions.UpdateNewCreditFailedException;
import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MainService {

    private static final Logger logger = LoggerFactory.getLogger(MainService.class);

    private final CustomerRepository customerRepository;

    public MainService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public ResponseMessage<List<Customer>> getCustomerByCriteria(final Map<String, String> params) {
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


    public ResponseMessage<Customer> addNewCustomer(final Customer newCustomer) {
        try {
            final Customer result = this.customerRepository.save(newCustomer);
            logger.info("Saving new customer is done.");
            return new ResponseMessage<>(HttpStatus.OK.value(), "Data is success to save on database", result);
        } catch (final Exception exception) {
            logger.error("There is an error while saving new customer on the database {0}", exception);
            throw new FailToSaveCustomerException();
        }
    }

    public ResponseMessage<UUID> removeCustomer(final UUID uuid) {
        logger.info("Deleting Customer {}", uuid);
        this.customerRepository.deleteById(uuid);
        logger.info("Deleting Customer {} is done", uuid);

        return new ResponseMessage<>(HttpStatus.OK.value(), "Delete Customer is Done", uuid);
    }

    @Transactional
    public ResponseMessage<?> updateUserCredit(final UUID customerId, final BigDecimal newCredit) {

        final Customer customer = this.customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);

        logger.info("Found Customer ");

        customer.setCreditLimit(newCredit);

        try {
            this.customerRepository.save(customer);
            logger.info("Customer id {} has now credit as {}", customer.getId(), newCredit);
            return new ResponseMessage<>(HttpStatus.OK.value(), "Done", customer);
        } catch (final Exception exception) {
            logger.error("Failed to update new credit {} of {}", newCredit, customer.getId());
            throw new UpdateNewCreditFailedException(newCredit, customer.getId());
        }


    }
}
