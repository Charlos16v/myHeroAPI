# Use an official OpenJDK 17 runtime as a parent image
FROM adoptopenjdk:17-jre-hotspot

# Set the working directory within the container
WORKDIR /app

# Copy the JAR file from the host into the container at the specified path
COPY target/myHero-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot application runs on (default is 8080)
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]
