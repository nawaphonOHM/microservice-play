package nawaphon.microservices.order_service.test_configuratiion;

import nawaphon.microservices.order_service.pojo.Order;
import nawaphon.microservices.order_service.repositories.OrderRepository;
import nawaphon.microservices.order_service.services.MainService;
import nawaphon.microservices.order_service.services.MainServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@TestConfiguration
public class MainServiceMock {

    @Bean
    public MainService mainService() {
        final var orderRepositoryMock = Mockito.mock(OrderRepository.class);

        final var testOrder = Mockito.mock(Order.class);

        Mockito.when(testOrder.getTotal()).thenReturn(new BigDecimal(1000));
        Mockito.when(testOrder.isStatus()).thenReturn(true);

        final var mockOrders = List.of(testOrder);

        Mockito.when(orderRepositoryMock.findAll()).thenReturn(mockOrders);

        return new MainServiceImpl(orderRepositoryMock);
    }
}
