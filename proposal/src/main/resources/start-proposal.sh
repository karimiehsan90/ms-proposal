#!/usr/bin/env bash

set -o errexit
BASEDIR=$(dirname $0)/..
APPNAME="proposal"
VERSION="1.0-SNAPSHOT"

cd "$BASEDIR"
jar uf "lib/$APPNAME-$VERSION.jar" -C conf/ .
mkdir -p logs
cd logs
java -jar "../lib/$APPNAME-$VERSION.jar"
