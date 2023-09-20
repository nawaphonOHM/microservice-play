package nawaphon.microservices.data_per_services.private_table_per_service.repositories;

import nawaphon.microservices.data_per_services.private_table_per_service.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> { }
