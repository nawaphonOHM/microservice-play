FROM openjdk:21-jdk


RUN useradd --create-home order_service

COPY build/libs/order_service-0.0.1.jar /home/order_service/
COPY entrypoint.sh /home/order_service/
COPY healthy_check.sh /home/order_service/

RUN chown order_service /home/order_service/entrypoint.sh &&  \
    chmod 500 /home/order_service/entrypoint.sh && \
    chown order_service /home/order_service/order_service-0.0.1.jar && \
    chmod 500 /home/order_service/order_service-0.0.1.jar && \
    chown order_service /home/order_service/healthy_check.sh && \
    chmod 500 /home/order_service/healthy_check.sh


EXPOSE 8080

USER order_service

WORKDIR /home/order_service/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
