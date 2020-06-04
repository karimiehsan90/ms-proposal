#!/usr/bin/env bash

set -o errexit

source ./ms-proposal-env.sh

unit-test() {
  mvn test
}

build() {
  mvn clean package -DskipTests

  for module in "${JAVA_MODULES[@]}"; do
    docker build \
      -t "ms-proposal/${module}" \
      -f "${module}/target/${module}/Dockerfile" \
      "${module}/target/${module}/"
  done

  for module in "${OTHER_LANGUAGES_MODULES[@]}"; do
    docker build \
      -t "ms-proposal/${module}" \
      -f "${module}/Dockerfile" \
      "${module}"
  done
}

_make-previous-docker-compose-down() {
  ls docker-compose.yml && docker-compose down
}

prepare-env() {
  mkdir -p "$HOME/ms-proposal"
  DIRECTORY=$PWD
  cd "$HOME/ms-proposal"
  _make-previous-docker-compose-down || true
  cp "${DIRECTORY}/docker-compose.yml" .
  docker-compose up -d
}

prepare-db() {
  docker run -i \
    --rm \
    --link mongo:mongo \
    --link web-proxy:web-proxy \
    -e PREPARE_DB_MONGO_HOST=mongo \
    --net msproposal_default \
    ms-proposal/prepare-db
}

acceptance-test() {
  docker run -i \
    --rm \
    --link mongo:mongo \
    --link web-proxy:web-proxy \
    -e ACCEPTANCE_TEST_APP_HOST=web-proxy \
    --net msproposal_default \
    ms-proposal/acceptance-test
}

parse-args() {
  METHOD=$1
  shift
}

main() {
  parse-args "${@}"
  $METHOD "${@}"
}

main "${@}"
