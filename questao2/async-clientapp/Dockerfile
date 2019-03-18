FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/async-clientapp-0.0.1-SNAPSHOT-jar-with-dependencies.jar async-clientapp-jar-with-dependencies.jar
COPY target/async-sender-0.0.1-SNAPSHOT-jar-with-dependencies.jar async-sender-jar-with-dependencies.jar

COPY run.sh run.sh

EXPOSE 10990

CMD ["sh", "run.sh"]