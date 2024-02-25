package nawaphon.microservices.event_sourcing.consumer.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nawaphon.microservices.event_sourcing.consumer.exceptions.UnclassifiedException;
import nawaphon.microservices.event_sourcing.consumer.pojo.Customer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderCustomerStreamComponent {

    private static final Logger logger = LoggerFactory.getLogger(OrderCustomerStreamComponent.class);

    private final ObjectMapper objectMapper;

    public OrderCustomerStreamComponent(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setUp(final StreamsBuilder streamsBuilder) {

        streamsBuilder.stream("orderCustomer", Consumed.with(Serdes.UUID(), Serdes.String())).groupByKey()
                .aggregate(() -> {
                    try {
                        return objectMapper.writeValueAsString(new Customer());
                    } catch (JsonProcessingException e) {
                        throw new UnclassifiedException("There is error while deserializing Customer Object", e);
                    }
                }, (var1, var2, var3) -> {
                    try {
                        final Customer example = objectMapper.readValue(var2, Customer.class);
                        final Customer previous = objectMapper.readValue(var3, Customer.class);

                        previous.setCreditLimit(previous.getCreditLimit().add(example.getCreditLimit()));
                        previous.setId(example.getId());

                        return objectMapper.writeValueAsString(previous);
                    } catch (JsonProcessingException e) {
                        throw new UnclassifiedException("There is error while deserializing Customer Object", e);
                    }
                }, Materialized.
                        <UUID, String, KeyValueStore<Bytes, byte[]>>as("orderCustomer")
                        .withKeySerde(Serdes.UUID()).withValueSerde(Serdes.String()));

    }
}