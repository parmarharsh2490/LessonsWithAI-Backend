# =========== BUILD STAGE ===========
FROM eclipse-temurin:24-jdk AS builder

WORKDIR /app

COPY . .

# Give gradlew execute permissions (required inside Linux container)
RUN chmod +x gradlew

# Build the Spring Boot jar
RUN ./gradlew clean build -x test


# =========== RUN STAGE ===========
FROM eclipse-temurin:24-jdk

WORKDIR /app

# Copy EXACT jar from builder stage
COPY --from=builder /app/build/libs/lessonswithai-0.0.1-SNAPSHOT.jar app/lessonswithai-0.0.1-SNAPSHOT.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app/lessonswithai-0.0.1-SNAPSHOT.jar"]