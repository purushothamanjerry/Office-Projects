# Use Java 17 runtime
FROM eclipse-temurin:17-jdk-jammy

# Create working directory
WORKDIR /app

# Copy your JAR from the backend folder into the container
COPY backend/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
