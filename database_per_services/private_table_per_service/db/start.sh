docker run --name 'some-postgres' --publish=5432:5432 --env POSTGRES_PASSWORD=test --detach postgres

docker cp ./initial.sql 'some-postgres':/

sleep 10

docker exec --detach 'some-postgres' psql -U postgres --file /initial.sql