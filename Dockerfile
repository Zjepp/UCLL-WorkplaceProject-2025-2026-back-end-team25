# Use an official Maven image as the base image
FROM maven:3.9.9-amazoncorretto-21 AS build
# Set the working directory in the container
WORKDIR /app

# Copy Maven settings for mirror configuration
COPY .mvn .mvn

# Copy the pom.xml and the project files to the container
COPY pom.xml .

# Download dependencies first (for better caching)
RUN mvn dependency:go-offline -B || mvn dependency:resolve -B || true

COPY src ./src

# Build the application using Maven with retry logic
RUN mvn clean package spring-boot:repackage -DskipTests -B
# Use Eclipse Temurin (recommended replacement for OpenJDK)
FROM eclipse-temurin:21-jre
# Set the working directory in the container
WORKDIR /app

# Create a non-root user to run the application
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/examen-BE-1.0-SNAPSHOT.jar .

# Change ownership of the application files to the non-root user
RUN chown -R appuser:appuser /app

# Switch to the non-root user
USER appuser

# Set the command to run the application
CMD ["java", "-jar", "examen-BE-1.0-SNAPSHOT.jar"]