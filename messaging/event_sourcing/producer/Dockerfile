FROM openjdk:21-jdk


RUN useradd --create-home producer

COPY ./build/libs/producer-0.0.1.jar /home/producer/
COPY ./entrypoint.sh /home/producer/

RUN chown producer /home/producer/entrypoint.sh &&  \
    chmod 500 /home/producer/entrypoint.sh && \
    chown producer /home/producer/producer-0.0.1.jar && \
    chmod 500 /home/producer/producer-0.0.1.jar


EXPOSE 8080

USER producer

WORKDIR /home/producer/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
