FROM amazoncorretto:21.0.9-al2023-headless


RUN useradd --create-home services_a

COPY build/libs/services_a-0.0.1.jar /home/services_a/
COPY entrypoint.sh /home/services_a/

RUN chown services_a /home/services_a/entrypoint.sh &&  \
    chmod 500 /home/services_a/entrypoint.sh && \
    chown services_a /home/services_a/services_a-0.0.1.jar && \
    chmod 500 /home/services_a/services_a-0.0.1.jar


EXPOSE 8761

USER services_a

WORKDIR /home/services_a/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
