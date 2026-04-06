package nawaphon.microservices.order_service.repositories;

import nawaphon.microservices.reusable.entity.shared.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
