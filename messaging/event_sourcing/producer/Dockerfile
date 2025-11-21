FROM amazoncorretto:21.0.9-al2023-headless


COPY ./build/libs/producer-0.0.1.jar /home/nobody/
COPY ./entrypoint.sh /home/nobody/

RUN chown nobody /home/nobody/entrypoint.sh &&  \
    chmod 500 /home/nobody/entrypoint.sh && \
    chown nobody /home/nobody/producer-0.0.1.jar && \
    chmod 500 /home/nobody/producer-0.0.1.jar


EXPOSE 8080

USER nobody

WORKDIR /home/nobody/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
