package nawaphon.microservices.customer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"nawaphon.microservice.shared_database.common.exception_advices"})
@EntityScan(basePackages = {"nawaphon.microservice.shared_database.common.repositories", "nawaphon.microservice.pojo"})
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

}
