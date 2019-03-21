#!/usr/bin/env bash

java -jar async-serverapp-jar-with-dependencies.jar&

sleep 2

java -jar async-receiver-jar-with-dependencies.jar
