FROM maven:3.9-eclipse-temurin-23 AS build

WORKDIR /app

COPY . .

RUN mvn -pl services/order-service -am clean package -DskipTests

FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY --from=build /app/services/order-service/target/app-order-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]