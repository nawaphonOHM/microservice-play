FROM postgres

COPY ./docker-entrypoint.sh /temp/
RUN cat /temp/docker-entrypoint.sh > /usr/local/bin/docker-entrypoint.sh && rm /temp/docker-entrypoint.sh

COPY ./initialze.sh /docker-entrypoint-initdb.d/



LABEL authors="nawaphon"
