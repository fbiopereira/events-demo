# Build
FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

# Runtime
FROM amazoncorretto:21.0.2-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8585
ENTRYPOINT ["java", "-jar", "app.jar"]
