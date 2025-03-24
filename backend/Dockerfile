FROM amazoncorretto:21.0.6-alpine3.21

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/boatapp-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application will run on (default Spring Boot port is 8080)
EXPOSE 8080

# Run the application JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
