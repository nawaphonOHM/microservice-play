#!/bin/bash

main() {

  export ERROR=0

  mandatoryEnvCheck

  if [ "$ERROR" -eq 1 ]; then
        exit 1
  fi

  unset ERROR

  java -jar /home/services_a/services_a-0.0.1.jar || exit 1
}

mandatoryEnvCheck() {
  ERROR=0

  if [ -z "$SERVICE_REGISTRY" ]; then
    cat >&2 <<-EOE
            Error: SERVICE_REGISTRY is required.
EOE
        ERROR=1
      fi
}



main
