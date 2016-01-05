#!/bin/sh
for tp in *.targetplatform; do
    mvn -Dtarget.definition=${tp} validate && rm -rf ./workspace
done
