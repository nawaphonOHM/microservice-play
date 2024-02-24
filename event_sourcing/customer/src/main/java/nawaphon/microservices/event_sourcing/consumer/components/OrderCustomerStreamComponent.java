package nawaphon.microservices.event_sourcing.consumer.components;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderCustomerStreamComponent {


    @Autowired
    public void setUp(final StreamsBuilder streamsBuilder) {
        streamsBuilder.stream("orderCustomer", Consumed.with(Serdes.UUID(), Serdes.String())).toTable(Materialized.as("orderCustomer"));
    }
}
