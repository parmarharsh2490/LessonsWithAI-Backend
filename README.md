# LessonsWithAI Backend

Spring Boot backend application for LessonsWithAI.

## Technology Stack

- Java 21 (LTS)
- Spring Boot 3.3.6
- Gradle 8.14.3
- MySQL Database
- Keycloak Authentication

## Docker Deployment

The application uses a multi-stage Docker build for optimal image size and security.

### Build the Docker Image

```bash
docker build -t lessonswithai-backend .
```

### Run Locally

```bash
docker run -p 8081:8081 lessonswithai-backend
```

## Deploying to Render

This application is optimized for deployment on Render.com.

### Environment Variables

Configure the following environment variables in Render:

| Variable | Description | Example |
|----------|-------------|---------|
| `PORT` | Application port (automatically set by Render) | `8081` |
| `SPRING_DATASOURCE_URL` | MySQL connection string | `jdbc:mysql://host:3306/db` |
| `SPRING_DATASOURCE_USERNAME` | Database username | `username` |
| `SPRING_DATASOURCE_PASSWORD` | Database password | `password` |
| `SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI` | Keycloak issuer URI | `https://keycloak.example.com/realms/realm` |
| `JAVA_OPTS` | JVM options (optional) | `-Xmx512m -Xms256m` |

### Dockerfile Features

- **Multi-stage build**: Separate build and runtime stages for smaller final image
- **Java 21 LTS**: Uses Eclipse Temurin for reliable long-term support
- **Security**: Runs as non-root user (`spring`)
- **Health checks**: Configured for Render's health monitoring
- **Dynamic port**: Supports `PORT` environment variable from Render
- **Optimized memory**: Configured for 512MB max heap

### Health Check Endpoint

The application exposes a health check endpoint at:
```
http://localhost:${PORT}/actuator/health
```

## Local Development

### Prerequisites

- Java 21 or higher
- MySQL 8.0 or higher
- Gradle 8.14+ (or use included wrapper)

### Build

```bash
./gradlew clean build
```

### Run

```bash
./gradlew bootRun
```

The application will start on port 8081 by default.

## Configuration

Application configuration is in `src/main/resources/application.yaml`.

For production, override these properties using environment variables or a production-specific configuration file.
