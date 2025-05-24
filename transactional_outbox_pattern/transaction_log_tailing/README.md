# Transactional Outbox Pattern with Debezium CDC

This project demonstrates the implementation of the Transactional Outbox Pattern in a Spring Boot microservice, enhanced with Debezium for Change Data Capture (CDC).

## What is the Transactional Outbox Pattern?

The Transactional Outbox Pattern is a solution to the problem of reliably publishing events/messages when updating a database within a transaction. It ensures that both the database update and the event publication are either both successful or both fail.

### The Problem

In a distributed system, when a service needs to update its database and publish an event to a message broker (like Kafka), there's a risk of inconsistency:

1. If the service updates the database first and then publishes the event, but the event publication fails, the database update has already been committed.
2. If the service publishes the event first and then updates the database, but the database update fails, the event has already been published.

In both cases, the system ends up in an inconsistent state.

### The Solution

The Transactional Outbox Pattern solves this problem by:

1. Storing the events to be published in an "outbox" table in the same database as the business entities.
2. Using a single transaction to update both the business entity and the outbox table.
3. Having a separate process that reads the outbox table and publishes the events to the message broker.

This ensures that the database update and the event are either both committed or both rolled back, maintaining consistency.

## What is Debezium and Change Data Capture (CDC)?

Debezium is an open-source distributed platform for change data capture. It monitors your databases, and when data changes, it captures those changes and streams them to Kafka. This allows other applications to respond to those changes immediately.

### Benefits of Using Debezium for CDC:

1. **Real-time Data Streaming**: Capture and stream database changes in real-time.
2. **Reduced Load on the Database**: Debezium reads the database transaction log, which is less resource-intensive than polling.
3. **Complete History**: Capture all changes, including intermediate states that might be missed by polling.
4. **Exactly-Once Semantics**: Ensure that each change is captured exactly once.

## Project Structure

The project consists of an Order Service that implements both the Transactional Outbox Pattern and Debezium CDC:

- `OrderService`: Handles order operations and creates outbox events in the same transaction.
- `OutboxEventProcessor`: Periodically checks for unprocessed outbox events and publishes them to Kafka.
- `DebeziumConfig`: Configures and starts the Debezium embedded engine.
- `DebeziumEventHandler`: Processes change events captured by Debezium and publishes them to Kafka.

## How to Run

### Prerequisites

1. Java 21 or higher
2. Gradle
3. PostgreSQL with logical replication enabled
4. Kafka broker running on localhost:9092 (or update the configuration in `application.properties`)
5. Docker and Docker Compose (for containerized deployment)

### Running with Docker Compose (Recommended)

The easiest way to run the application is using Docker Compose, which will set up all the required dependencies:

1. Navigate to the transactional_outbox_pattern directory:
   ```bash
   cd transactional_outbox_pattern
   ```

2. Start the application and its dependencies:
   ```bash
   docker-compose up -d
   ```

3. The application will be available at http://localhost:8080

4. To stop the application:
   ```bash
   docker-compose down
   ```

5. To stop the application and remove all data:
   ```bash
   docker-compose down -v
   ```

### Manual Setup (Without Docker)

#### PostgreSQL Setup for Debezium

1. Edit your `postgresql.conf` file to enable logical replication:
   ```
   wal_level = logical
   max_wal_senders = 10
   max_replication_slots = 10
   ```

2. Restart PostgreSQL to apply these changes.

3. Create a database for the application:
   ```sql
   CREATE DATABASE orderdb;
   ```

4. After starting the application for the first time (which will create the tables), create a replication slot and publication:
   ```sql
   SELECT pg_create_logical_replication_slot('debezium_slot', 'pgoutput');
   CREATE PUBLICATION dbz_publication FOR TABLE orders, outbox_events;
   ```

#### Running the Application Locally

1. Run the application:
   ```bash
   cd transactional_outbox_pattern/order_service
   ./gradlew bootRun
   ```

2. The application will start with both the traditional outbox pattern and Debezium CDC enabled.

## API Endpoints

The Order Service exposes the following REST endpoints:

- `POST /api/orders`: Create a new order
- `PUT /api/orders/{orderNumber}/status?status=NEW_STATUS`: Update the status of an order
- `GET /api/orders/{orderNumber}`: Get an order by its order number
- `GET /api/orders`: Get all orders
- `GET /api/orders/customer/{customerId}`: Get all orders for a specific customer

## Example Usage

### Create an Order

```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId": 123, "totalAmount": 99.99}'
```

### Update Order Status

```bash
curl -X PUT "http://localhost:8080/api/orders/your-order-number/status?status=PAID"
```

## Implementation Details

### Transactional Outbox Pattern Implementation

1. When an order is created or updated, an outbox event is created in the same transaction.
2. The `OutboxEventProcessor` runs periodically to check for unprocessed events.
3. It publishes these events to Kafka and marks them as processed.
4. If Kafka is temporarily unavailable, the events remain in the outbox table and will be processed in the next run.

### Debezium CDC Implementation

1. Debezium is configured to monitor the PostgreSQL database for changes.
2. When changes occur in the `orders` table, Debezium captures them from the transaction log.
3. The `DebeziumEventHandler` processes these change events and publishes them to Kafka.
4. This provides real-time event streaming without the need for polling.

### Dual Approach Benefits

By implementing both the Transactional Outbox Pattern and Debezium CDC, this project offers:

1. **Reliability**: The outbox pattern ensures that database updates and event publishing are atomic.
2. **Real-time Updates**: Debezium provides immediate streaming of changes.
3. **Reduced Load**: Debezium reads the transaction log rather than polling the database.
4. **Fallback Mechanism**: If one approach fails, the other can still ensure events are published.

### Database Schema

The application uses two main tables:

1. `orders`: Stores order information
2. `outbox_events`: Stores events to be published to Kafka

### PostgreSQL Configuration for Debezium

To use Debezium with PostgreSQL, the database must be configured for logical replication:

1. Set `wal_level=logical` in postgresql.conf
2. Create a replication slot: `SELECT pg_create_logical_replication_slot('debezium_slot', 'pgoutput');`
3. Create a publication: `CREATE PUBLICATION dbz_publication FOR TABLE orders, outbox_events;`

### Docker Setup

The project includes Docker configuration for easy deployment:

#### Dockerfile

The `Dockerfile` in the `order_service` directory uses a multi-stage build approach:
1. **Build Stage**: Uses eclipse-temurin:21-jdk to compile the application
2. **Runtime Stage**: Uses eclipse-temurin:21-jre to run the application

Key features:
- Environment variables for configuration
- Proper setup for Debezium offset storage
- Optimized for smaller image size

#### Docker Compose

The `docker-compose.yml` file sets up the complete environment:
1. **PostgreSQL**: Configured with logical replication for Debezium
2. **Zookeeper**: Required for Kafka
3. **Kafka**: Message broker for event publishing
4. **Order Service**: The Spring Boot application

Benefits of using Docker Compose:
- All dependencies are automatically configured
- Services start in the correct order with health checks
- Environment variables are pre-configured
- Data persistence with Docker volumes

### Technologies Used

- Spring Boot 3.1.5
- Spring Data JPA
- PostgreSQL (with logical replication enabled)
- Spring Kafka
- Debezium 2.3.3.Final (with PostgreSQL connector)
- Lombok
- Docker & Docker Compose
