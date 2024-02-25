package nawaphon.microservices.event_sourcing.consumer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nawaphon.microservices.event_sourcing.consumer.exceptions.UnclassifiedException;
import nawaphon.microservices.event_sourcing.consumer.pojo.Customer;
import nawaphon.microservices.event_sourcing.consumer.pojo.CustomerId;
import nawaphon.microservices.event_sourcing.consumer.pojo.ResponseMessage;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MainService {

    private static final Logger LOGGER = LogManager.getLogger(MainService.class);

    private final KafkaTemplate<UUID, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;

    public MainService(final KafkaTemplate<UUID, String> kafkaTemplate, final ObjectMapper objectMapper, final StreamsBuilderFactoryBean streamsBuilderFactoryBean) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.streamsBuilderFactoryBean = streamsBuilderFactoryBean;
    }

    public Mono<ResponseMessage<Customer>> addCustomer(final Customer customer) {

        final UUID newCustomerUUID = UUID.randomUUID();

        customer.setId(newCustomerUUID);

        return Mono.create((val1) -> {
            final String valueToBeWritten;

            try {
                valueToBeWritten = objectMapper.writeValueAsString(customer);
            } catch (JsonProcessingException e) {
                LOGGER.error("There is an error while serializing Customer Object", e);
                val1.error(new UnclassifiedException("There is error while serializing Customer Object", e));
                return;
            }

            kafkaTemplate.send("orderCustomer", newCustomerUUID, valueToBeWritten)
                    .addCallback((result) -> {
                        assert result != null;
                        try {
                            val1.success(
                                    new ResponseMessage<>(
                                            HttpStatus.OK.value(),
                                            HttpStatus.OK.toString(),
                                            objectMapper.readValue(result.getProducerRecord().value(), Customer.class)
                                    ));
                        } catch (JsonProcessingException e) {
                            val1.error(new RuntimeException(e));
                        }
                    }, (ex) -> {
                        LOGGER.error("There is an error while serializing Customer Object", ex);
                        val1.error(new UnclassifiedException("There is error while saving a customer object", ex));
                    });
        });
    }

    public ResponseMessage<Customer> searchCustomerById(final CustomerId customerId) {
        final KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();
        assert kafkaStreams != null;
        final ReadOnlyKeyValueStore<UUID, String> readOnlyKeyValueStore = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType("orderCustomer", QueryableStoreTypes.keyValueStore())
        );

        try {
            return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                    objectMapper.readValue(readOnlyKeyValueStore.get(customerId.getId()), Customer.class));

        } catch (JsonProcessingException e) {
            throw new UnclassifiedException("There is error while deserializing Customer Object", e);
        }
    }

    public ResponseMessage<List<Customer>> searchCustomers() {
        final KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();
        assert kafkaStreams != null;
        final ReadOnlyKeyValueStore<UUID, String> readOnlyKeyValueStore = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType("orderCustomer", QueryableStoreTypes.keyValueStore())
        );

        final KeyValueIterator<UUID, String> iterator = readOnlyKeyValueStore.all();
        final List<Customer> customers = new ArrayList<>();
        while (iterator.hasNext()) {
            try {
                customers.add(objectMapper.readValue(iterator.next().value, Customer.class));
            } catch (JsonProcessingException e) {
                throw new UnclassifiedException("There is error while deserializing Customer Object", e);
            }
        }
        iterator.close();

        return new ResponseMessage<>(HttpStatus.OK.value(), HttpStatus.OK.toString(), customers);
    }
}