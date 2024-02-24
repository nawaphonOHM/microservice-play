package nawaphon.microservices.event_sourcing.consumer.components;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderCustomerStreamComponent {


    @Autowired
    public void setUp(final StreamsBuilder streamsBuilder) {

        streamsBuilder.stream("orderCustomer", Consumed.with(Serdes.UUID(), Serdes.String())).toTable(
                Materialized.<UUID, String, KeyValueStore<Bytes, byte[]>>
                        as("orderCustomer").withKeySerde(Serdes.UUID()).withValueSerde(Serdes.String())
        );

    }
}
