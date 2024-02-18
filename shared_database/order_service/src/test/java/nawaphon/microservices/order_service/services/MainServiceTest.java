package nawaphon.microservices.order_service.services;

import nawaphon.microservices.order_service.test_configurations.MainServiceMock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MainServiceMock.class)
public class MainServiceTest {

    @Autowired
    private RequiredTransactionalService requiredTransactionalService;

    @Autowired
    private NonRequiredTransactionalService nonRequiredTransactionalService;

    @Autowired
    private MainService mainService;


}