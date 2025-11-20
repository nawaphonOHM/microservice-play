FROM amazoncorretto:21.0.9-al2023-headless


RUN useradd --create-home customer_service

COPY build/libs/customer_service-0.0.1.jar /home/customer_service/
COPY entrypoint.sh /home/customer_service/

RUN chown customer_service /home/customer_service/entrypoint.sh &&  \
    chmod 500 /home/customer_service/entrypoint.sh && \
    chown customer_service /home/customer_service/customer_service-0.0.1.jar && \
    chmod 500 /home/customer_service/customer_service-0.0.1.jar


EXPOSE 8080

USER customer_service

WORKDIR /home/customer_service/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
