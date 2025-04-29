# Start from a base Java image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 9090

# Run the JAR file
CMD ["java", "-jar", "app.jar"]
