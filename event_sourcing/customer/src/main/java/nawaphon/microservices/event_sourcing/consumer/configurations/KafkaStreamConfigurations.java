package nawaphon.microservices.event_sourcing.consumer.configurations;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaStreamConfigurations {


    private final StreamsBuilder streamsBuilder;

    public KafkaStreamConfigurations(final StreamsBuilder streamsBuilder) {
        this.streamsBuilder = streamsBuilder;
        buildOrderCustomerStream();
    }

    private void buildOrderCustomerStream() {
        streamsBuilder.stream("orderCustomer").toTable(Materialized.as("orderCustomer"));
    }
}
