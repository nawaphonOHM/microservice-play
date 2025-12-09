package nawaphon.microservices.messaging.rest.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class HttpChangeAspect {

    private static final Logger log = LoggerFactory.getLogger(HttpChangeAspect.class);

    @Before("execution(* nawaphon.microservices.messaging.rest.aspect.*.*(..))")
    public void logHttpChange(@NonNull JoinPoint joinPoint) {
        log.info("HTTP call: {}", joinPoint.getSignature().getName());
    }
}
