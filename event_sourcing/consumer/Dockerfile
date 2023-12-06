FROM openjdk:11.0.16-jre


RUN useradd --create-home consumer

COPY ./build/libs/consumer-0.0.1.jar /home/consumer/
COPY ./entrypoint.sh /home/consumer/

RUN chown producer /home/consumer/entrypoint.sh &&  \
    chmod 500 /home/consumer/entrypoint.sh && \
    chown producer /home/consumer/consumer-0.0.1.jar && \
    chmod 500 /home/consumer/consumer-0.0.1.jar


EXPOSE 8080

USER consumer

WORKDIR /home/consumer/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"