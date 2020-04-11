# Docker file for two phase build
# Phase 1 - Build the application in it's own container named "build"
FROM openjdk:8-jdk-alpine as build
VOLUME /tmp
COPY . .
RUN ./gradlew clean build

# Phase 2 - Build the actual docker container with only the jar file
FROM openjdk:8-jdk-alpine
WORKDIR /app
# Copy file from the "build container identified in line 3
COPY --from=build build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080

# Build docker image
# $ docker build -t user/review-service .
#
# Deploy joke service locally
# $ run -p 8080:8080 user/review-service
# should be available at http://localhost:8080/api/review
#
# Push to dockerhub
# $ docker login
# $ docker tag jokeservice user/review-service
# $ docker push user/review-service
#
# Retrieve the image from docker hub
# $ docker pull user/review-service