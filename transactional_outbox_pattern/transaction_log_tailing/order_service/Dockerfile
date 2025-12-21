FROM ubuntu/jre:21-24.04
WORKDIR /
COPY build/libs/order_service-0.0.1.jar .
COPY build/libs/heart_beat-0.0.1.jar .

HEALTHCHECK CMD ["java", "-jar", "heart_beat-0.0.1.jar"]

ENTRYPOINT ["java", "-jar", "order_service-0.0.1.jar"]

LABEL authors="nawaphon"
