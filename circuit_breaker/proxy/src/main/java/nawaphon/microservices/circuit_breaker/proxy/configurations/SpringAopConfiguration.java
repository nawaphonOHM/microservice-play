package nawaphon.microservices.circuit_breaker.proxy.configurations;

import nawaphon.microservices.circuit_breaker.proxy.aspect.HttpChangeAspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
class SpringAopConfiguration {


    public HttpChangeAspect httpChangeAspect() {
        return null;
    }

}
