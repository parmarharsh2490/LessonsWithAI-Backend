# ---- Stage 1: Build the JAR ----
FROM gradle:8.10.2-jdk-24 AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

# ---- Stage 2: Run the JAR ----
FROM eclipse-temurin:24-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
