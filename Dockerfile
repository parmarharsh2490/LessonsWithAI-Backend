# ============================
# 1. Build Stage
# ============================
FROM gradle:8.7-jdk24 AS builder

WORKDIR /app

# Copy Gradle config first (better build caching)
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Download dependencies (cached)
RUN ./gradlew dependencies --no-daemon || return 0

# Copy source
COPY . .

# Build application
RUN ./gradlew clean bootJar --no-daemon

# ============================
# 2. Run Stage
# ============================
FROM eclipse-temurin:24-jre

WORKDIR /app

# Copy JAR from builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port (Render uses 8080)
EXPOSE 8080

# Run Spring Boot
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "app.jar"]
