package nawaphon.microservices.event_sourcing.consumer.components;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderCustomerStreamComponent {


    @Autowired
    public void setUp(final StreamsBuilder streamsBuilder) {
        streamsBuilder.stream("orderCustomer").toTable(Materialized.as("orderCustomer"));
    }
}
