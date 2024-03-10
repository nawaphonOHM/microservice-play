#!/bin/bash

main() {

  export ERROR=0

  mandatoryEnvCheck

  if [ "$ERROR" -eq 1 ]; then
        exit 1
  fi

  unset ERROR

  java -jar /home/consumer/consumer-0.0.1.jar || exit 1
}

mandatoryEnvCheck() {
  ERROR=0

  if [ -z "$FRIEND_IP" ]; then
    cat >&2 <<-EOE
        Error: $FRIEND_IP is required.
EOE
    ERROR=1
  fi
}



main
