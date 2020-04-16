#!/usr/bin/env bash

set -o errexit

DIRECTORY=$(dirname $0)

dockerfiles=$(ls $DIRECTORY/*/Dockerfile)
for dockerfile in $dockerfiles; do
    image=$(echo $dockerfile | cut -d'/' -f2)
    docker build \
        -t "ms-proposal/$image" \
        -f "$DIRECTORY/$image/Dockerfile" \
        "$DIRECTORY/$image"
done
