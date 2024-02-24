package nawaphon.microservices.event_sourcing.consumer.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaStreamConfigurations {

    @Bean
    public NewTopic createOrderCustomerTopic() {
        return TopicBuilder.name("orderCustomer").build();
    }
}
