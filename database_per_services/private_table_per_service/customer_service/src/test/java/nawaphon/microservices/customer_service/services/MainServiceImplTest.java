package nawaphon.microservices.customer_service.services;

import nawaphon.microservices.customer_service.pojo.Customer;
import nawaphon.microservices.customer_service.pojo.ResponseMessage;
import nawaphon.microservices.customer_service.test_configuration.MainServiceMock;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
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

    @Test
    void assertResponseMessageHasAllValidPropertyTest() {
        AssertionsForClassTypes.assertThat(testResult).extracting(ResponseMessage::code, ResponseMessage::message)
                .containsExactly(200, "Done");
    }

    @Test
    void shouldHasOnlyOneCustomerObjectTest() {
        Assertions.assertEquals(1, testResult.results().size());
    }

    @Test
    void assertCustomerHasAllExpectedPropertyTest() {
        AssertionsForClassTypes.assertThat(testResult.results().get(0)).extracting(Customer::getCreditLimit)
                .isEqualTo(new BigDecimal(1000));
    }
}
