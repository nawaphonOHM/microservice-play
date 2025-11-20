FROM amazoncorretto:21.0.9-al2023-headless


RUN useradd --create-home receiver

COPY ./build/libs/receiver-0.0.1.jar /home/receiver/
COPY ./entrypoint.sh /home/receiver/

RUN chown receiver /home/receiver/entrypoint.sh &&  \
    chmod 500 /home/receiver/entrypoint.sh && \
    chown receiver /home/receiver/receiver-0.0.1.jar && \
    chmod 500 /home/receiver/receiver-0.0.1.jar


EXPOSE 8080

USER receiver

WORKDIR /home/receiver/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
