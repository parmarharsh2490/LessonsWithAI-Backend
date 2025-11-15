# ---- Stage 1: Build the JAR ----
FROM gradle:8.14-jdk21 AS build

WORKDIR /app

# Copy all files needed for build
COPY build.gradle settings.gradle ./
COPY gradle gradle/
COPY src src/

# Build the application using system gradle
RUN gradle clean bootJar --no-daemon

# ---- Stage 2: Run the JAR ----
FROM eclipse-temurin:21-jre
WORKDIR /app

# Create a non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# Copy the built JAR from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port (Render will override this with PORT env var)
EXPOSE 8081

# Add healthcheck
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:${PORT:-8081}/actuator/health || exit 1

# Use environment variables for Java options
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Run the application
# Use PORT environment variable if available (Render provides this)
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8081} -jar app.jar"]
