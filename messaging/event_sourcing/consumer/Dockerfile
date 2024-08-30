FROM openjdk:21-jdk


RUN useradd --create-home consumer

COPY ./build/libs/consumer-0.0.1.jar /home/consumer/
COPY ./entrypoint.sh /home/consumer/

RUN chown consumer /home/consumer/entrypoint.sh &&  \
    chmod 500 /home/consumer/entrypoint.sh && \
    chown consumer /home/consumer/consumer-0.0.1.jar && \
    chmod 500 /home/consumer/consumer-0.0.1.jar


EXPOSE 8080

USER consumer

WORKDIR /home/consumer/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
