FROM openjdk:21-jdk


RUN useradd --create-home sender

COPY ./build/libs/sender-0.0.1.jar /home/sender/
COPY ./entrypoint.sh /home/sender/

RUN chown sender /home/sender/entrypoint.sh &&  \
    chmod 500 /home/sender/entrypoint.sh && \
    chown sender /home/sender/sender-0.0.1.jar && \
    chmod 500 /home/sender/sender-0.0.1.jar


EXPOSE 8080

USER sender

WORKDIR /home/sender/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
