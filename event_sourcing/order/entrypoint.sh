#!/bin/bash

main() {

  export ERROR=0

  mandatoryEnvCheck

  if [ "$ERROR" -eq 1 ]; then
        exit 1
  fi

  unset ERROR

  java -jar /home/order/order-0.0.1.jar || exit 1
}

mandatoryEnvCheck() {
  ERROR=0

  if [ -z "$KAFKA_BOOSTRAP_SERVERS"  ]; then
    cat >&2 <<-EOE
    Error: KAFKA_BOOSTRAP_SERVERS is required.
EOE
  ERROR=1
  fi
}



main