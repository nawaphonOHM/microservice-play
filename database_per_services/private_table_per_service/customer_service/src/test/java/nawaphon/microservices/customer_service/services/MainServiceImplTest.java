package nawaphon.microservices.customer_service.services;

import nawaphon.microservices.customer_service.test_configuration.MainServiceMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import(MainServiceMock.class)
class MainServiceImplTest {


    @Autowired
    private MainService mainService;


    @Test
    void firstServiceTest() {
    }
}