FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/async-sender-0.0.1-SNAPSHOT-jar-with-dependencies.jar async-sender-jar-with-dependencies.jar

EXPOSE 10990

CMD ["java", "-jar", "async-sender-jar-with-dependencies.jar"]