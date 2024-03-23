FROM openjdk:11.0.16-jre


RUN useradd --create-home sereal_servicender

COPY ./build/libs/real_service-0.0.1.jar /home/real_service/
COPY ./entrypoint.sh /home/real_service/

RUN chown real_service /home/real_service/entrypoint.sh &&  \
    chmod 500 /home/real_service/entrypoint.sh && \
    chown real_service /home/real_service/real_service-0.0.1.jar && \
    chmod 500 /home/real_service/real_service-0.0.1.jar


EXPOSE 8080

USER real_service

WORKDIR /home/real_service/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
