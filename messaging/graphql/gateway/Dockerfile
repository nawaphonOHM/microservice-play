FROM amazoncorretto:21.0.9-al2023-headless


RUN useradd --create-home gateway

COPY ./build/libs/gateway-0.0.1.jar /home/gateway/
COPY ./entrypoint.sh /home/gateway/

RUN chown gateway /home/gateway/entrypoint.sh &&  \
    chmod 500 /home/gateway/entrypoint.sh && \
    chown gateway /home/gateway/gateway-0.0.1.jar && \
    chmod 500 /home/gateway/gateway-0.0.1.jar


EXPOSE 8080

USER gateway

WORKDIR /home/gateway/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
