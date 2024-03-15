FROM openjdk:11.0.16-jre


RUN useradd --create-home receiver

COPY ./build/libs/receiver-0.0.1.jar /home/receiver/
COPY ./entrypoint.sh /home/receiver/

RUN chown receiver /home/receiver/entrypoint.sh &&  \
    chmod 500 /home/receiver/entrypoint.sh && \
    chown producer /home/receiver/receiver-0.0.1.jar && \
    chmod 500 /home/receiver/receiver-0.0.1.jar


EXPOSE 8080

USER producer

WORKDIR /home/producer/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
