package nawaphon.microservices.event_sourcing.consumer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nawaphon.microservices.event_sourcing.consumer.exceptions.UnclassifiedException;
import nawaphon.microservices.event_sourcing.consumer.pojo.Customer;
import nawaphon.microservices.event_sourcing.consumer.pojo.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class MainService {

    private final KafkaTemplate<UUID, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    public MainService(final KafkaTemplate<UUID, String> kafkaTemplate, final ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    public ResponseMessage<Customer> addCustomer(final Customer customer) {

        final UUID newCustomerUUID = UUID.randomUUID();

        customer.setId(newCustomerUUID);

        final Future<SendResult<UUID, String>> sendResultFuture;
        try {
            sendResultFuture = kafkaTemplate.send("order-customer", newCustomerUUID, objectMapper.writeValueAsString(customer));
        } catch (JsonProcessingException e) {
            throw new UnclassifiedException("There is error while serializing Customer Object", e);
        }

        while (!sendResultFuture.isDone()) {
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (final InterruptedException e) {
                throw new UnclassifiedException("Unable to sleep for waiting saving a result to message queue", e);
            }
        }


        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), customer);
    }
}
