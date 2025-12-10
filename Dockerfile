# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for caching dependencies)
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Make mvnw executable
RUN chmod +x mvnw

# Download Maven dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the source code
COPY src src

# Package the Spring Boot application (skip tests for faster build)
RUN ./mvnw package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/BankApplicationSystem-0.0.1-SNAPSHOT.jar"]
