FROM amazoncorretto:21.0.9-al2023-headless


COPY build/libs/order_service-0.0.1.jar /home/nobody/
COPY entrypoint.sh /home/nobody/
COPY healthy_check.sh /home/nobody/

RUN chown nobody /home/nobody/entrypoint.sh &&  \
    chmod 500 /home/nobody/entrypoint.sh && \
    chown nobody /home/nobody/order_service-0.0.1.jar && \
    chmod 500 /home/nobody/order_service-0.0.1.jar && \
    chown nobody /home/nobody/healthy_check.sh && \
    chmod 500 /home/nobody/healthy_check.sh


EXPOSE 8080

USER nobody

WORKDIR /home/nobody/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
