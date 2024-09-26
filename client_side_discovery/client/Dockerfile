FROM openjdk:21-jdk


RUN useradd --create-home client

COPY build/libs/client-0.0.1.jar /home/client/
COPY entrypoint.sh /home/client/

RUN chown client /home/client/entrypoint.sh &&  \
    chmod 500 /home/client/entrypoint.sh && \
    chown client /home/client/client-0.0.1.jar && \
    chmod 500 /home/client/client-0.0.1.jar


EXPOSE 8761

USER client

WORKDIR /home/client/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
