#!/bin/bash


RESULT=$(curl 'http://localhost:8080/transactional_outbox_pattern/order-service/actuator/health' -i -X GET -H 'Accept: application/json' | grep -Eo '[A-Z]+')

if [ "$RESULT" != "UP" ]; then
  exit 1
fi

exit 0