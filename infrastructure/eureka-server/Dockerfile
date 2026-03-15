FROM maven:3.9-eclipse-temurin-23 AS build

WORKDIR /app

COPY . .

RUN mvn -pl infrastructure/eureka-server -am clean package -DskipTests

FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY --from=build /app/infrastructure/eureka-server/target/app-eureka-server.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]