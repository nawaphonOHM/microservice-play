package nawaphon.microservices.order_service.services;

import nawaphon.microservices.order_service.test_configurations.MainServiceMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
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


}