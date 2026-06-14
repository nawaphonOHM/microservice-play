# --- Build stage: extract jar and create an AppCDS archive via a training run ---
FROM bellsoft/liberica-openjre-alpine:21-cds AS optimizer
WORKDIR /app
COPY ./build/libs/sender-0.0.1.jar /tmp/app.jar
# Extract into a regular classpath layout (app.jar launcher + lib/), which CDS can archive.
RUN java -Djarmode=tools -jar /tmp/app.jar extract --destination /app
# Training run: refresh the context and exit, dumping the archive. The 'cds' profile
# supplies dummy config so bean creation finishes without real network/DB access.
RUN java -XX:ArchiveClassesAtExit=/app/application.jsa \
    -Dspring.context.exit=onRefresh -Dspring.profiles.active=cds -jar /app/app.jar

# --- Runtime stage: same /app path so the archived classpath matches ---
FROM bellsoft/liberica-openjre-alpine:21-cds
WORKDIR /app
COPY --from=optimizer /app/ /app/
EXPOSE 8080
ENTRYPOINT ["java", "-XX:SharedArchiveFile=/app/application.jsa", "-jar", "/app/app.jar"]

LABEL authors="nawaphon"
