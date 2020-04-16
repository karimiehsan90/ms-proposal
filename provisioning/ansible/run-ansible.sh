#!/usr/bin/env bash

set -o errexit

DIRECTORY=$(dirname $0)
echo $DIRECTORY
PLAYBOOK=$1
INVENTORY=$2

docker run -it \
    --name ansible-runner \
    --rm \
    --net host \
    -w /etc/ms-proposal \
    -v $PWD/$DIRECTORY:/etc/ms-proposal \
    ms-proposal/ansible-runner \
        ansible-playbook playbooks/$PLAYBOOK \
            -i inventories/$INVENTORY --ask-vault-pass
