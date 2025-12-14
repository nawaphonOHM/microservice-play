FROM ubuntu/jre:21-24.04
WORKDIR /
COPY build/libs/order_service-0.0.1.jar .
COPY healthy_check.sh .
RUN chmod +x /healthy_check.sh \
    && apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*
ENTRYPOINT ["java", "-jar", "order_service-0.0.1.jar"]

LABEL authors="nawaphon"
