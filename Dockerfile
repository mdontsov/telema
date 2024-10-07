# Use an official JDK as the base image
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the build jar from the build context to the container
COPY build/libs/telema.jar /app/telema.jar

# Expose the port the application will run on (for instance 1 or 2)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "telema.jar"]
