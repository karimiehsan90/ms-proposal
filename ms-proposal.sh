#!/usr/bin/env bash

set -o errexit

source ./ms-proposal-env.sh

unit-test() {
  mvn test
}

build() {
  mvn clean package -DskipTests

  for module in "${modules[@]}"; do
    docker build \
      -t "ms-proposal/${module}" \
      -f "${module}/target/${module}/Dockerfile" \
      "${module}/target/${module}/"
  done
}

prepare-env() {
  echo "Not implemented"
}

acceptance-test() {
  echo "Not implemented"
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
