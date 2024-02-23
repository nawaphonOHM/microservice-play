FROM openjdk:11.0.16-jre


RUN useradd --create-home customer

COPY ./build/libs/customer-0.0.1.jar /home/customer/
COPY ./entrypoint.sh /home/customer/

RUN chown customer /home/customer/entrypoint.sh &&  \
    chmod 500 /home/customer/entrypoint.sh && \
    chown customer /home/customer/customer-0.0.1.jar && \
    chmod 500 /home/customer/customer-0.0.1.jar


EXPOSE 8080

USER customer

WORKDIR /home/customer/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"