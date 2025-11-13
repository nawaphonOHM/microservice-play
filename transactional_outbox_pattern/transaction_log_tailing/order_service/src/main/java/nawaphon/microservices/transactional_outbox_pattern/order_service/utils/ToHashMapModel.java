package nawaphon.microservices.transactional_outbox_pattern.order_service.utils;

import nawaphon.microservices.transactional_outbox_pattern.order_service.exception.ToHashMpModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class ToHashMapModel {

    private static final Logger log = LoggerFactory.getLogger(ToHashMapModel.class);

    public static Map<String, Object> convert(final Object object) {
        final var map = new HashMap<String, Object>();

        for (final var field : object.getClass().getDeclaredFields()) {
            final var key = field.getName();

            try {
                final var value = object.getClass()
                        .getDeclaredMethod("get" + key.substring(0, 1).toUpperCase() + key.substring(1))
                        .invoke(object);

                map.put(key, value);

            } catch (final IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                log.error("Unable to convert object to map", e);
                throw new ToHashMpModelException(e);
            }

        }

        return map;
    }
}
