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
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

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

    public Mono<ResponseMessage<?>> addCustomer(@NotNull final Customer customer) {

        final UUID newCustomerUUID = UUID.randomUUID();

        customer.setId(newCustomerUUID);

        final String valueToBeWritten;

        try {
            valueToBeWritten = objectMapper.writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            LOGGER.error("There is an error while serializing Customer Object", e);
            throw new UnclassifiedException("There is error while serializing Customer Object", e);
        }

        return Mono.<SendResult<UUID, String>>create((var1) -> {
                    try {
                        var1.success(kafkaTemplate.send("orderCustomer", newCustomerUUID, valueToBeWritten).get());
                    } catch (InterruptedException | ExecutionException e) {
                        LOGGER.error("There is an error while saving a customer object", e);
                        var1.error(new UnclassifiedException("There is an error while saving a Customer Object", e));

                    }
                })
                .map((result) -> {
                    try {
                        return new ResponseMessage<>(
                                HttpStatus.OK.value(),
                                HttpStatus.OK.toString(),
                                objectMapper.readValue(result.getProducerRecord().value(), Customer.class));
                    } catch (JsonProcessingException e) {
                        LOGGER.error("There is an error while saving a customer object", e);
                        return new ResponseMessage<>(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                                null);
                    }
                }).subscribeOn(Schedulers.boundedElastic());
    }

    public ResponseMessage<Customer> searchCustomerById(final @NotNull CustomerId customerId) {
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