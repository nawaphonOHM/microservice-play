#!/bin/bash

echo start initializing...

docker compose up -d --wait

# This part is order database initialization

DOCKER_COMPOSE_NAME=transactional_outbox_pattern

DOCKER_CONFIG_PATH=$(docker compose ls --format json | jq ".[] | select(.Name==\"$DOCKER_COMPOSE_NAME\") | \
.ConfigFiles" | sed "s/\"//g")

DOCKER_CONFIG_PATH_DIR_NAME=$(dirname "$DOCKER_CONFIG_PATH")

source "$DOCKER_CONFIG_PATH_DIR_NAME"/order_service_database/order_service_database.env

LOCAL_HOST_IP=0.0.0.0

ORDER_DATABASE_CONTAINER_NAME=transactional_outbox_pattern-orders_database

ORDER_SERVICE_PORT=$(docker compose ps --format json | jq "select(.Name | \
test(\"$ORDER_DATABASE_CONTAINER_NAME\")) | .Publishers | .[] | select(.URL==\"0.0.0.0\") | .PublishedPort" | \
sed "s/\"//g")

PGPASSWORD=$POSTGRES_PASSWORD psql -h $LOCAL_HOST_IP -p "$ORDER_SERVICE_PORT" -U "$POSTGRES_USER" -a \
-f "$DOCKER_CONFIG_PATH_DIR_NAME"/necessary_query_for_order_database.sql

DOCKER_NETWORK_NAME=transactional_outbox_pattern_default

ORDER_DATABASE_CONTAINER_IP=$(docker network inspect "$DOCKER_NETWORK_NAME" --format json | jq ".[] | .Containers | .[] | \
select(.Name | test(\"$ORDER_DATABASE_CONTAINER_NAME\")) | .IPv4Address" | sed -e "s/\"//g" | sed -E "s/\/[0-9]{2}//")

ORDER_SERVICE_REGISTER_INFORMATION_FORDEBEZIUM=$(sed "s/{{ip}}/$ORDER_DATABASE_CONTAINER_IP/" \
 "$DOCKER_CONFIG_PATH_DIR_NAME"/debezium_cdc_register_for_order_service.json | sed "s/{{database_username}}/$POSTGRES_USER/" |
 sed "s/{{database_username}}/$POSTGRES_PASSWORD/")

 DEBEZIUM_SERVICE_NAME=transactional_outbox_pattern-debezium

DEBEZIUM_SERVICE_PORT=$(docker compose ps --format json | jq "select(.Name | \
test(\"$DEBEZIUM_SERVICE_NAME\")) | .Publishers | .[] | select(.URL==\"0.0.0.0\") | .PublishedPort" | \
sed "s/\"//g")

curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" \
 "$LOCAL_HOST_IP":"$DEBEZIUM_SERVICE_PORT"/connectors/ -d "$ORDER_SERVICE_REGISTER_INFORMATION_FORDEBEZIUM"

 # This part is order database initialization done






