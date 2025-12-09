package nawaphon.microservices.circuit_breaker.proxy.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class HttpChangeAspect {

    @Before("execution(* nawaphon.microservices.circuit_breaker.proxy.http_exchanges.*.*(..))")
    public void logHttpChange() {

    }
}
