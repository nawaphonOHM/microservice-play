#!/bin/bash


RESULT=$(curl 'http://localhost:8080/transactional_outbox_pattern/order-service/actuator/health' -o /dev/null -w "%{http_code}" \
-s -i -X GET )

if [ "$RESULT" != "200" ]; then
  exit 1
fi

exit 0