package nawaphon.microservices.order_service.services;

import nawaphon.microservices.order_service.pojo.Customer;
import nawaphon.microservices.order_service.pojo.Order;
import nawaphon.microservices.order_service.test_configurations.MainServiceMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MainServiceMock.class)
public class MainServiceTest {

    @Autowired
    private RequiredTransactionalService requiredTransactionalService;

    @Autowired
    private NonRequiredTransactionalService nonRequiredTransactionalService;

    @Autowired
    private MainService mainService;


    @Test
    public void testGetOrderByCriteriaCallRightService() {
        final Map<String, String> map = new HashMap<>();
        mainService.getOrderByCriteria(map);

        Mockito.verify(nonRequiredTransactionalService, Mockito.times(1)).getOrderByCriteria(Mockito.same(map));
    }


    @Test
    public void testAddOrdersCallRightService() {
        final Order order1 = Mockito.mock(Order.class);
        Mockito.when(order1.getCustomerId()).thenReturn(Mockito.mock(Customer.class));

        final Order order2 = Mockito.mock(Order.class);
        Mockito.when(order2.getCustomerId()).thenReturn(Mockito.mock(Customer.class));

        final Order order3 = Mockito.mock(Order.class);
        Mockito.when(order3.getCustomerId()).thenReturn(Mockito.mock(Customer.class));


        final List<Order> orders = List.of(order1);

        mainService.addOrders(orders);

        Mockito.verify(requiredTransactionalService, Mockito.times(orders.size())).addOrder(Mockito.same(order1));
    }


}