FROM mcr.microsoft.com/java/jdk:8u192-zulu-ubuntu

RUN mkdir /opt/ms-proposal

COPY . /opt/ms-proposal/

RUN chmod +x /opt/ms-proposal/bin/start-auth.sh

ENTRYPOINT [ "/opt/ms-proposal/bin/start-auth.sh" ]
