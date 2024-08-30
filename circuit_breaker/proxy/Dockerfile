FROM openjdk:21-jdk


RUN useradd --create-home proxy_user

COPY ./build/libs/proxy-0.0.1.jar /home/proxy_user/
COPY ./entrypoint.sh /home/proxy_user/

RUN chown proxy_user /home/proxy_user/entrypoint.sh &&  \
    chmod 500 /home/proxy_user/entrypoint.sh && \
    chown proxy_user /home/proxy_user/proxy-0.0.1.jar && \
    chmod 500 /home/proxy_user/proxy-0.0.1.jar


EXPOSE 8080

USER proxy_user

WORKDIR /home/proxy_user/

ENTRYPOINT ["./entrypoint.sh"]

LABEL authors="nawaphon"
