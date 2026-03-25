FROM maven:3.9-eclipse-temurin-23 AS build

WORKDIR /app

COPY . .

RUN mvn -pl services/notification-service -am clean package -DskipTests

FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY --from=build /app/services/notification-service/target/app-notification-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]