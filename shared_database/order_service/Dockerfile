FROM amazoncorretto:21.0.9-al2023-headless


RUN useradd --create-home order_service

COPY build/libs/order_service-0.0.1.jar /home/order_service/
COPY entrypoint.sh /home/order_service/

RUN chown order_service /home/order_service/entrypoint.sh &&  \
    chmod 500 /home/order_service/entrypoint.sh && \
    chown order_service /home/order_service/order_service-0.0.1.jar && \
    chmod 500 /home/order_service/order_service-0.0.1.jar


EXPOSE 8080

USER order_service

WORKDIR /home/order_service/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
