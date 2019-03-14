#!/usr/bin/env bash

cd async-share
mvn clean install
cd ..

cd async-clientapp
mvn clean compile assembly:single
#mvn clean package
#docker build -t sd/async-clientapp .
cd ..

cd async-receiver-pull
mvn clean compile assembly:single
#mvn clean package
#docker build -t sd/async-receiver-pull .
cd ..

cd async-sender-pull
mvn clean compile assembly:single
#mvn clean package
#docker build -t sd/async-sender-pull .
cd ..

cd async-serverappl
mvn clean compile assembly:single
#mvn clean package
#docker build -t sd/async-serverapp .
cd ..


#docker run --it --name sender-pull sd/sync-sender-pull:latest
#docker run --it --name receiver-pull sd/sync-receiver-pull:latest
#docker run --it --name serverapp sd/sync-serverapp:latest
#docker run --it --name clientapp sd/sync-clientapp:latest
