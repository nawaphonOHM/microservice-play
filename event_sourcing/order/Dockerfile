FROM openjdk:11.0.16-jre


RUN useradd --create-home order

COPY ./build/libs/order-0.0.1.jar /home/order/
COPY ./entrypoint.sh /home/order/

RUN chown order /home/order/entrypoint.sh &&  \
    chmod 500 /home/order/entrypoint.sh && \
    chown order /home/order/order-0.0.1.jar && \
    chmod 500 /home/order/order-0.0.1.jar


EXPOSE 8080

USER order

WORKDIR /home/order/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"