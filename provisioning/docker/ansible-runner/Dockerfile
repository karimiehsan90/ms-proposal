FROM ubuntu:18.04

COPY requirements.txt /requirements.txt

RUN apt update \
    && apt install -y --no-install-recommends \
        python3 \
        python3-pip \
        python3-setuptools \
    && pip3 install -r /requirements.txt
