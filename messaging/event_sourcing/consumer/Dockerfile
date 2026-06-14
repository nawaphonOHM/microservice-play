FROM bellsoft/liberica-openjre-alpine:21-cds
WORKDIR /
COPY ./build/libs/consumer-0.0.1.jar .
ENTRYPOINT ["java", "-XX:+AutoCreateSharedArchive", "-XX:SharedArchiveFile=app.jsa", "-jar", "consumer-0.0.1.jar"]

LABEL authors="nawaphon"
