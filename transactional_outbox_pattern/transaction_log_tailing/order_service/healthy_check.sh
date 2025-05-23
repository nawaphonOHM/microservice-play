#!/bin/bash


RESULT=$(curl 'http://localhost:8080/transactional_outbox_pattern/order-service/actuator/health' -s -i -X GET -H \
'Accept: application/json' | grep -Eo '\{\"status\":\"([A-Z]+)\"\}' | sed -E 's/\{\"status\":\"([A-Z]+)\"\}/\1/')

if [ "$RESULT" != "UP" ]; then
  exit 1
fi

exit 0