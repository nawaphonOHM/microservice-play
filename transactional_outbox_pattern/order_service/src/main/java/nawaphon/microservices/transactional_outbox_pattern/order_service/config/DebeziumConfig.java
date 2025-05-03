package nawaphon.microservices.transactional_outbox_pattern.order_service.config;

import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.debezium.inbound.DebeziumMessageProducer;
import org.springframework.messaging.MessageChannel;


@Configuration
public class DebeziumConfig {

    @Bean
    public MessageChannel debeziumInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer debeziumMessageProducer(final DebeziumEngine.Builder<ChangeEvent<byte[], byte[]>> debeziumEngineBuilder, final MessageChannel debeziumInputChannel) {
        final DebeziumMessageProducer debeziumMessageProducer = new DebeziumMessageProducer(debeziumEngineBuilder);

        debeziumMessageProducer.setOutputChannel(debeziumInputChannel);

        return debeziumMessageProducer;

    }
}
