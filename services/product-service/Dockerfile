FROM maven:3.9-eclipse-temurin-23 AS build

WORKDIR /app

COPY . .

RUN mvn -pl services/product-service -am clean package -DskipTests

FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY --from=build /app/services/product-service/target/app-product-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]