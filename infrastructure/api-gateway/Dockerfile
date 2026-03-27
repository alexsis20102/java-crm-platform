FROM maven:3.9-eclipse-temurin-23 AS build

WORKDIR /app

COPY . .

RUN mvn -f /app/pom.xml clean package -DskipTests

FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY --from=build /app/infrastructure/api-gateway/target/app-gateway.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]