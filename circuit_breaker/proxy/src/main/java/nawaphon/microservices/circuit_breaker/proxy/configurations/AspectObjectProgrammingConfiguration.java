package nawaphon.microservices.circuit_breaker.proxy.configurations;

import nawaphon.microservices.circuit_breaker.proxy.aspect.HttpChangeAspect;
import org.springframework.context.annotation.Configuration;


@Configuration
class AspectObjectProgrammingConfiguration {

    public HttpChangeAspect httpChangeAspect() {
        return null;
    }

}
