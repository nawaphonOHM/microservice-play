package nawaphon.microservices.customer_service.services;

import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.test_configuration.MainServiceMock;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MainServiceMock.class})
class MainServiceImplTest {


    @Autowired
    private MainService mainService;

    private ResponseMessage<List<Customer>> testResult;

    @BeforeEach
    void setUp() {
        testResult = mainService.firstService();
    }

    @Test
    void responseMessageShouldNotNullTest() {
        AssertionsForClassTypes.assertThat(testResult).isNotNull();
    }

    @Test
    void listInsideResponseMessageShouldNotNullTest() {
        AssertionsForClassTypes.assertThat(testResult).isNotNull();
    }
}