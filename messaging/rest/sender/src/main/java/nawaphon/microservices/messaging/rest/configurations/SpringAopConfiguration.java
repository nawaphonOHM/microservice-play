package nawaphon.microservices.messaging.rest.configurations;

import nawaphon.microservices.messaging.rest.aspect.HttpChangeAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class SpringAopConfiguration {


    @Bean
    public HttpChangeAspect httpChangeAspect() {
        return null;
    }

}
