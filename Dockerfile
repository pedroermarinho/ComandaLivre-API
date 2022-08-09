FROM maven:3.8-openjdk-17 AS builder

WORKDIR /app

COPY ./pom.xml /app/pom.xml

RUN mvn dependency:go-offline

COPY . /app

RUN mvn package -DskipTes


FROM openjdk:17-jdk-alpine3.14

COPY --from=builder /app/target/comanda-livre-api-0.0.1-SNAPSHOT.jar /comanda-livre-api-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-Dserver.port=9090", "-Djava.security.egd=file:/dev/./urandom", "-jar","-Dspring.profiles.active=docker", "/comanda-livre-api-0.0.1-SNAPSHOT.jar"]