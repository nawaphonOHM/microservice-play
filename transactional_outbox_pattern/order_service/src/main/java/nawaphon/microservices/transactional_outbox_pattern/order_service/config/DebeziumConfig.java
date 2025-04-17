package nawaphon.microservices.transactional_outbox_pattern.order_service.config;

import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import nawaphon.microservices.transactional_outbox_pattern.order_service.service.DebeziumEventHandler;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Configuration for Debezium embedded engine.
 * This class sets up Debezium to capture changes from PostgreSQL and stream them to Kafka.
 */
@Configuration
@Slf4j
public class DebeziumConfig {

    @Value("${debezium.enabled:false}")
    private boolean debeziumEnabled;

    @Value("${debezium.connector.name}")
    private String connectorName;

    @Value("${debezium.database.hostname}")
    private String databaseHostname;

    @Value("${debezium.database.port}")
    private String databasePort;

    @Value("${debezium.database.user}")
    private String databaseUser;

    @Value("${debezium.database.password}")
    private String databasePassword;

    @Value("${debezium.database.dbname}")
    private String databaseName;

    @Value("${debezium.database.server.name}")
    private String databaseServerName;

    @Value("${debezium.offset.storage}")
    private String offsetStorage;

    @Value("${debezium.offset.storage.file.filename}")
    private String offsetStorageFileName;

    @Value("${debezium.offset.flush.interval.ms}")
    private String offsetFlushIntervalMs;

    @Value("${debezium.topic.prefix}")
    private String topicPrefix;

    private DebeziumEngine<SourceRecord> engine;
    private final Executor executor = Executors.newSingleThreadExecutor();

    /**
     * Creates and configures a Debezium embedded engine.
     *
     * @param debeziumEventHandler the handler for Debezium change events
     * @return the configured Debezium engine
     */
    @Bean
    public DebeziumEngine<SourceRecord> debeziumEngine(final DebeziumEventHandler debeziumEventHandler) {
        if (!debeziumEnabled) {
            log.info("Debezium is disabled");
            return null;
        }

        log.info("Configuring Debezium embedded engine");

        final Configuration configuration = Configuration.create()
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("name", connectorName)
                .with("database.hostname", databaseHostname)
                .with("database.port", databasePort)
                .with("database.user", databaseUser)
                .with("database.password", databasePassword)
                .with("database.dbname", databaseName)
                .with("database.server.name", databaseServerName)
                .with("offset.storage", offsetStorage)
                .with("offset.storage.file.filename", offsetStorageFileName)
                .with("offset.flush.interval.ms", offsetFlushIntervalMs)
                .with("topic.prefix", topicPrefix)
                .with("plugin.name", "pgoutput")
                .with("slot.name", "debezium_slot")
                .with("publication.name", "dbz_publication")
                .with("table.include.list", "public.orders,public.outbox_events")
                .with("schema.include.list", "public")
                .build();

        this.engine = DebeziumEngine.create(Connect.class)
                .using(configuration.asProperties())
                .notifying(record -> {
                    log.debug("Received change event: {}", record);
                    // Process the change event using the DebeziumEventHandler
                    debeziumEventHandler.handleChangeEvent(record);
                })
                .build();

        return this.engine;
    }

    /**
     * Starts the Debezium engine when the application starts.
     */
    @PostConstruct
    public void start() {
        if (debeziumEnabled && this.engine != null) {
            log.info("Starting Debezium embedded engine");
            this.executor.execute(this.engine);
        }
    }

    /**
     * Stops the Debezium engine when the application stops.
     */
    @PreDestroy
    public void stop() {
        if (debeziumEnabled && this.engine != null) {
            log.info("Stopping Debezium embedded engine");
            try {
                this.engine.close();
            } catch (final IOException e) {
                log.error("Error stopping Debezium engine", e);
            }
        }
    }
}
