# Use an official OpenJDK image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the build.gradle and settings.gradle to the container
COPY build.gradle settings.gradle /app/

# Copy the entire project to the container
COPY . /app/

# Build the project
RUN ./gradlew build --no-daemon

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/build/libs/telema.jar"]
