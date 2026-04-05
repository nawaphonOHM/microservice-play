package nawaphon.microservices.client_side_discovery.client.configuration;


import nawaphon.microservices.client_side_discovery.client.http_exchanges.ServiceAExchange;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.net.URI;

@Configuration
public class ClientConfiguration {

    @Bean
    public ServiceAExchange serviceAExchange(final RestClient.Builder builder) {
        final var restClient = builder.baseUrl(URI.create("http://serviceA/client-side-discovery/service-a/")).build();

        return HttpServiceProxyFactory.builderFor(
                        RestClientAdapter.create(restClient)
                )
                .build()
                .createClient(ServiceAExchange.class);
    }
}
