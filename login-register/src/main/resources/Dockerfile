FROM mcr.microsoft.com/java/jdk:8u192-zulu-ubuntu

RUN mkdir /opt/ms-proposal

ENV LOGIN_REGISTER_SERVER_PORT=8080 \
    LC_ALL=C.UTF-8

COPY . /opt/ms-proposal/

RUN chmod +x /opt/ms-proposal/bin/start-login-register.sh

EXPOSE 8080

ENTRYPOINT [ "/opt/ms-proposal/bin/start-login-register.sh" ]
