FROM m.docker-registry.ir/node:12.3.1

RUN mkdir /opt/ms-proposal

ENV LC_ALL=C.UTF-8 \
    HOST=0.0.0.0

COPY . /opt/ms-proposal

WORKDIR /opt/ms-proposal

RUN npm ci \
    && npm run build

EXPOSE 4000

ENTRYPOINT ["npm", "run", "start"]
