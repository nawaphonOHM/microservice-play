FROM ubuntu/jre:21-24.04


WORKDIR /

COPY ./build/libs/sender-0.0.1.jar .


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "sender-0.0.1.jar"]

LABEL authors="nawaphon"
