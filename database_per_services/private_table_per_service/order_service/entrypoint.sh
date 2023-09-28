#!/bin/bash

main() {

  export ERROR=0

  mandatoryEnvCheck

  if [ "$ERROR" -eq 1 ]; then
        exit 1
  fi

  unset ERROR

  java -jar /home/order_service/order_service-0.0.1.jar || exit 1
}

mandatoryEnvCheck() {
  if [ -z "$DATABASE_URL" ]; then
      cat >&2 <<-'EOE'
      Error: DATABASE_URL is required.
EOE
    ERROR=1
    fi

    if [ -z "$DATABASE_USERNAME" ]; then
      cat >&2 <<-'EOE'
      Error: DATABASE_USERNAME is required.
EOE
    ERROR=1
    fi

    if [ -z "$DATABASE_PASSWORD" ]; then
      cat >&2 <<-'EOE'
      Error: DATABASE_PASSWORD is required.
EOE
    ERROR=1
    fi
}



main