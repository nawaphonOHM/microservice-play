FROM openjdk:11.0.16-jre


RUN useradd --create-home services_registry

COPY build/libs/services_registry-0.0.1.jar /home/services_registry/
COPY entrypoint.sh /home/services_registry/

RUN chown services_registry /home/services_registry/entrypoint.sh &&  \
    chmod 500 /home/services_registry/entrypoint.sh && \
    chown services_registry /home/services_registry/services_registry-0.0.1.jar && \
    chmod 500 /home/services_registry/services_registry-0.0.1.jar


EXPOSE 8080

USER services_registry

WORKDIR /home/services_registry/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
