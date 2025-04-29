# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy everything into the container
COPY . .

# Build the Spring Boot app
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy only the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (Render injects it)
EXPOSE 8080

# Start the app
CMD ["java", "-jar", "app.jar"]
