FROM maven:3.8-openjdk-17 AS builder

WORKDIR /aires

COPY ./pom.xml /aires/pom.xml

RUN mvn dependency:go-offline

COPY . /aires

RUN mvn package



FROM openjdk:17-jdk-alpine3.14

COPY --from=builder /aires/target/ComandaLivre-API-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-Dserver.port=8080", "-Djava.security.egd=file:/dev/./urandom", "-jar","-Dspring.profiles.active=docker", "/app.jar"] 
