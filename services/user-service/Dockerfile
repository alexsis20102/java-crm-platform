FROM maven:3.9-eclipse-temurin-23 AS build

WORKDIR /app

COPY . .

RUN mvn -pl services/user-service -am clean package -DskipTests

FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY --from=build /app/services/user-service/target/app-user-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]