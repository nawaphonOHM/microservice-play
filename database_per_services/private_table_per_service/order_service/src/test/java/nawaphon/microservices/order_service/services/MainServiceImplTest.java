package nawaphon.microservices.order_service.services;

import nawaphon.microservices.order_service.pojo.Order;
import nawaphon.microservices.order_service.pojo.ResponseMessage;
import nawaphon.microservices.order_service.test_configuratiion.MainServiceMock;
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
@ContextConfiguration(classes = MainServiceMock.class)
class MainServiceImplTest {

    @Autowired
    private MainService mainService;

    private ResponseMessage<List<Order>> testResult;

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
        AssertionsForClassTypes.assertThat(testResult).extracting(ResponseMessage::getCode, ResponseMessage::getMessage)
                .containsExactly(200, "Done");
    }

    @Test
    void shouldHasOnlyOneCustomerObjectTest() {
        Assertions.assertEquals(1, testResult.getResults().size());
    }

    @Test
    void assertCustomerHasAllExpectedPropertyTest() {
        AssertionsForClassTypes.assertThat(testResult.getResults().get(0)).extracting(Order::getTotal, Order::isStatus).containsExactly(new BigDecimal(1000), true);
    }
}