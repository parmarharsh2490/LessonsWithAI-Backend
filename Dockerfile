# ---- Build stage (use JDK 21) ----
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY gradlew gradlew
COPY gradle gradle
RUN chmod +x gradlew

COPY build.gradle settings.gradle ./
COPY gradle.properties gradle.properties || true
RUN ./gradlew dependencies --no-daemon || true

COPY . .

RUN ./gradlew clean bootJar --no-daemon

# ---- Run stage (use JRE 21) ----
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

# Run Spring Boot
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "app.jar"]
