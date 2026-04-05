package nawaphon.microservices.client_side_discovery.client.http_exchanges;

import org.springframework.web.service.annotation.GetExchange;

public interface ServiceAExchange {

    @GetExchange("/hello-world")
    String helloWorld();
}
