FROM openjdk:21-jdk


RUN useradd --create-home service_registry

COPY build/libs/service_registry-0.0.1.jar /home/service_registry/
COPY entrypoint.sh /home/service_registry/

RUN chown service_registry /home/service_registry/entrypoint.sh &&  \
    chmod 500 /home/service_registry/entrypoint.sh && \
    chown service_registry /home/service_registry/service_registry-0.0.1.jar && \
    chmod 500 /home/service_registry/service_registry-0.0.1.jar


EXPOSE 8761

USER service_registry

WORKDIR /home/service_registry/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
