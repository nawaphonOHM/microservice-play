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
}



main
