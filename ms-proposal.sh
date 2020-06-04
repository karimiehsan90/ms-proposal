#!/usr/bin/env bash

set -o errexit

source ./ms-proposal-env.sh

unit-test() {
  mvn test
}

build() {
  if [ "$ENV" = 'ci' ]; then
    image_prefix="ci"
  else
    image_prefix="cd"
  fi
  mvn clean package -DskipTests

  for module in "${JAVA_MODULES[@]}"; do
    docker build \
      -t "${image_prefix}/ms-proposal/${module}" \
      -f "${module}/target/${module}/Dockerfile" \
      "${module}/target/${module}/"
  done

  for module in "${OTHER_LANGUAGES_MODULES[@]}"; do
    docker build \
      -t "${image_prefix}/ms-proposal/${module}" \
      -f "${module}/Dockerfile" \
      "${module}"
  done
}

_make-previous-docker-compose-down() {
  ls docker-compose.yml && docker-compose down
}

prepare-env() {
  if [ "$ENV" = 'ci' ]; then
    compose_dir=$HOME/ms-proposal
  else
    compose_dir=$HOME/release
  fi
  mkdir -p "${compose_dir}"
  DIRECTORY=$PWD
  cd "${compose_dir}"
  _make-previous-docker-compose-down || true
  cp "${DIRECTORY}/docker-compose.yml" .
  cp "${DIRECTORY}/${ENV}.env" .env
  docker-compose up -d
}

prepare-db() {
  if [ "$ENV" = 'ci' ]; then
    network=msproposal_default
    image_prefix="ci"
  else
    network=release_default
    image_prefix="cd"
  fi
  docker run -i \
    --rm \
    --link mongo:mongo \
    --link web-proxy:web-proxy \
    -e PREPARE_DB_MONGO_HOST=mongo \
    --net "${network}" \
    "${image_prefix}/ms-proposal/prepare-db"
}

acceptance-test() {
  docker run -i \
    --rm \
    --link mongo:mongo \
    --link web-proxy:web-proxy \
    -e ACCEPTANCE_TEST_APP_HOST=web-proxy \
    --net msproposal_default \
    ci/ms-proposal/acceptance-test
}

parse-args() {
  METHOD=$1
  ENV=$2
  shift
  shift
}

main() {
  parse-args "${@}"
  $METHOD "${@}"
}

main "${@}"
