package nawaphon.microservices.messaging.rest.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.service.annotation.GetExchange;

@Aspect
public class HttpChangeAspect {

    private static final Logger log = LoggerFactory.getLogger(HttpChangeAspect.class);

    @Before("execution(* nawaphon.microservices.messaging.rest.http_exchanges.*.*(..))")
    public void logHttpChange(@NonNull JoinPoint joinPoint) {
        final var annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(GetExchange.class);

        if (annotation == null) {
            return;
        }

        final var value = annotation.value().replaceAll("\\{.*}", joinPoint.getArgs()[0].toString());

        log.info("HTTP call: {}", value);
    }
}
