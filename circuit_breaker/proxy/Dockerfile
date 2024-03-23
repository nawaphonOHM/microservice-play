FROM openjdk:11.0.16-jre


RUN useradd --create-home proxy

COPY ./build/libs/receiver-0.0.1.jar /home/proxy/
COPY ./entrypoint.sh /home/proxy/

RUN chown proxy /home/proxy/entrypoint.sh &&  \
    chmod 500 /home/proxy/entrypoint.sh && \
    chown proxy /home/proxy/proxy-0.0.1.jar && \
    chmod 500 /home/proxy/proxy-0.0.1.jar


EXPOSE 8080

USER proxy

WORKDIR /home/proxy/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
