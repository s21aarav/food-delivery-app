# Use an official Java environment
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy all your project files into the container
COPY . .

# Give Maven permission to run, then build the app
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Tell Render to use our port and start the app
EXPOSE 8085
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]