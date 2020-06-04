FROM m.docker-registry.ir/python:3.6.9

COPY requirements.txt /requirements.txt

RUN pip install -r /requirements.txt

ENV LC_ALL=C.UTF-8

COPY main.py /main.py

ENTRYPOINT ["python", "/main.py"]
