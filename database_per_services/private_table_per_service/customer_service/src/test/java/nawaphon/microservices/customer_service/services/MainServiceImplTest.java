package nawaphon.microservices.customer_service.services;

import nawaphon.microservices.customer_service.test_configuration.MainServiceMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MainServiceMock.class})
class MainServiceImplTest {


    @Autowired
    private MainService mainService;


    @Test
    void firstServiceTest() {
    }
}