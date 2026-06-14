FROM bellsoft/liberica-openjre-alpine:21-cds


WORKDIR /

COPY ./build/libs/receiver-0.0.1.jar .


EXPOSE 8080


ENTRYPOINT ["java", "-XX:+AutoCreateSharedArchive", "-XX:SharedArchiveFile=app.jsa", "-jar", "receiver-0.0.1.jar"]

LABEL authors="nawaphon"
