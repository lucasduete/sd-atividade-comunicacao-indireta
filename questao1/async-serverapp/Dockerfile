FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/async-serverapp-0.0.1-SNAPSHOT-jar-with-dependencies.jar async-serverapp-jar-with-dependencies.jar

EXPOSE 10992

CMD ["java", "-jar", "async-serverapp-jar-with-dependencies.jar"]