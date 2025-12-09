# Use Java 21 runtime to match how the JAR was compiled
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY backend/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
