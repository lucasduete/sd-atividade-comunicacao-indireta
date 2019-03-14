FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/async-receiver-0.0.1-SNAPSHOT-jar-with-dependencies.jar async-receiver-jar-with-dependencies.jar

EXPOSE 10991

CMD ["java", "-jar", "async-receiver-jar-with-dependencies.jar"]