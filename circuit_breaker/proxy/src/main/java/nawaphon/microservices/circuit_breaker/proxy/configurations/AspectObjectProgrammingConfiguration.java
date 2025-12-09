package nawaphon.microservices.circuit_breaker.proxy.configurations;

import nawaphon.microservices.circuit_breaker.proxy.aspect.HttpChangeAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class AspectObjectProgrammingConfiguration {


    @Bean
    public HttpChangeAspect httpChangeAspect() {
        return new HttpChangeAspect();
    }

}
