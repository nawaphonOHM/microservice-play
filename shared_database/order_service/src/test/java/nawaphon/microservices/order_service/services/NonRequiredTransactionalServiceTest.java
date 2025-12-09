package nawaphon.microservices.order_service.services;

import nawaphon.microservices.order_service.repositories.OrderRepository;
import nawaphon.microservices.order_service.test_configurations.MainServiceMock2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MainServiceMock2.class)
public class NonRequiredTransactionalServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private NonRequiredTransactionalService nonRequiredTransactionalService;


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testGetOrderByCriteriaMethodUUIDIsSetWhenThereIs() {
        final var map = new HashMap<String, String>();

        final var uuidMock = UUID.randomUUID();

        map.put("id", uuidMock.toString());

        nonRequiredTransactionalService.getOrderByCriteria(map);

        Mockito.verify(orderRepository, Mockito.times(1)).findBy(Mockito.argThat((probe) -> probe.getProbe().getId().toString().equals(uuidMock.toString())), Mockito.any());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testGetOrderByCriteriaMethodCustomerIdIsSetWhenThereIs() {
        final var map = new HashMap<String, String>();

        final var uuidMock = UUID.randomUUID();

        map.put("customerId", uuidMock.toString());

        nonRequiredTransactionalService.getOrderByCriteria(map);

        Mockito.verify(orderRepository, Mockito.times(1)).findBy(Mockito.argThat((probe) -> probe.getProbe().getCustomerId().getId().toString().equals(uuidMock.toString())), Mockito.any());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testGetOrderByCriteriaMethodStatusIsSetWhenThereIs() {
        final var map = new HashMap<String, String>();

        final var statusMock = true;

        map.put("status", String.valueOf(statusMock));

        nonRequiredTransactionalService.getOrderByCriteria(map);

        Mockito.verify(orderRepository, Mockito.times(1)).findBy(Mockito.argThat((probe) -> probe.getProbe().isStatus() == statusMock), Mockito.any());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testGetOrderByCriteriaMethodTotalIsSetWhenThereIs() {
        final var map = new HashMap<String, String>();

        final var moneyMock = new BigDecimal("999.99");

        map.put("total", String.valueOf(moneyMock));

        nonRequiredTransactionalService.getOrderByCriteria(map);

        Mockito.verify(orderRepository, Mockito.times(1)).findBy(Mockito.argThat((probe) -> probe.getProbe().getTotal().toString().equals(moneyMock.toString())), Mockito.any());
    }

}