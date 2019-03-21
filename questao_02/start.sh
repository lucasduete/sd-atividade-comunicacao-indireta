#!/usr/bin/env bash

cd async-share
mvn clean compile install
cd ..

cd async-clientapp
mvn clean compile assembly:single
cd ..

cd async-sender-pull
mvn clean compile assembly:single
mv target/async-sender-0.0.1-SNAPSHOT-jar-with-dependencies.jar ../async-clientapp/target/
cd ..

cd async-serverapp
mvn clean compile assembly:single
cd ..

cd async-receiver-pull
mvn clean compile assembly:single
mv target/async-receiver-0.0.1-SNAPSHOT-jar-with-dependencies.jar ../async-serverapp/target/
cd ..
