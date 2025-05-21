#!/bin/bash


REULST=$(curl 'http://localhost:8080/actuator/health' -i -X GET -H 'Accept: application/json' | jq '.status')

if [ "$REULST" != "UP" ]; then
  exit 1
fi

exit 0