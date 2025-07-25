#!/bin/bash

echo start initializing...

docker compose up -d --wait

if [[ $? != 0 ]]; then
  echo "run docker failed"
  exit 1
fi

# This part is order database initialization

DOCKER_COMPOSE_NAME=transaction_log_tailing

DOCKER_CONFIG_PATH=$(docker compose ls --format json | jq ".[] | select(.Name==\"$DOCKER_COMPOSE_NAME\") | \
.ConfigFiles" | sed "s/\"//g")

echo "DOCKER_CONFIG_PATH = $DOCKER_CONFIG_PATH"

DOCKER_CONFIG_PATH_DIR_NAME=$(dirname "$DOCKER_CONFIG_PATH")

echo "DOCKER_CONFIG_PATH_DIR_NAME = $DOCKER_CONFIG_PATH_DIR_NAME"

source "$DOCKER_CONFIG_PATH_DIR_NAME"/order_service_database/order_service_database.env

echo "run $DOCKER_CONFIG_PATH_DIR_NAME/order_service_database/order_service_database.env is successful"

LOCAL_HOST_IP=0.0.0.0

ORDER_DATABASE_CONTAINER_NAME=transaction_log_tailing-orders_database

ORDER_SERVICE_PORT=$(docker compose ps --format json | jq "select(.Name | \
test(\"$ORDER_DATABASE_CONTAINER_NAME\")) | .Publishers | .[] | select(.URL==\"$LOCAL_HOST_IP\") | .PublishedPort" | \
sed "s/\"//g")

echo "ORDER_SERVICE_PORT = $ORDER_SERVICE_PORT"

PGPASSWORD=$POSTGRES_PASSWORD psql -h $LOCAL_HOST_IP -p "$ORDER_SERVICE_PORT" -U "$POSTGRES_USER" -a \
-f "$DOCKER_CONFIG_PATH_DIR_NAME"/necessary_query_for_order_database.sql

DOCKER_NETWORK_NAME=transaction_log_tailing_default

ORDER_DATABASE_CONTAINER_IP=$(docker network inspect "$DOCKER_NETWORK_NAME" --format json | jq ".[] | .Containers | .[] | \
select(.Name | test(\"$ORDER_DATABASE_CONTAINER_NAME\")) | .IPv4Address" | sed -e "s/\"//g" | sed -E "s/\/[0-9]{2}//")

echo "ORDER_DATABASE_CONTAINER_IP = $ORDER_DATABASE_CONTAINER_IP"

ORDER_SERVICE_REGISTER_INFORMATION_FORDEBEZIUM=$(sed "s/{{ip}}/$ORDER_DATABASE_CONTAINER_IP/" \
 "$DOCKER_CONFIG_PATH_DIR_NAME"/debezium_cdc_register_for_order_service.json | sed "s/{{database_username}}/$POSTGRES_USER/" |
 sed "s/{{database_username}}/$POSTGRES_PASSWORD/")

 echo "ORDER_SERVICE_REGISTER_INFORMATION_FORDEBEZIUM = $ORDER_SERVICE_REGISTER_INFORMATION_FORDEBEZIUM"

 DEBEZIUM_SERVICE_NAME=transaction_log_tailing-debezium

DEBEZIUM_SERVICE_PORT=$(docker compose ps --format json | jq "select(.Name | \
test(\"$DEBEZIUM_SERVICE_NAME\")) | .Publishers | .[] | select(.URL==\"$LOCAL_HOST_IP\") | .PublishedPort" | \
sed "s/\"//g")

echo "DEBEZIUM_SERVICE_PORT = $DEBEZIUM_SERVICE_PORT"

curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" \
 "$LOCAL_HOST_IP":"$DEBEZIUM_SERVICE_PORT"/connectors/ -d "$ORDER_SERVICE_REGISTER_INFORMATION_FORDEBEZIUM"

if [[ $? != 0 ]]; then
   echo "curl run failed"
   exit 1
fi

 # This part is order database initialization done

 echo initializing...DONE






