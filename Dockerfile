FROM ubuntu:latest
LABEL authors="vanz"

# Use a base image with Java 21 installed
FROM openjdk:21-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy the Maven wrapper files
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file to the working directory
COPY pom.xml .

# Download the project dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code to the working directory
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Set the command to run the Spring Boot application
CMD ["java", "-jar", "target/java-library-service-0.0.1-SNAPSHOT.jar"]