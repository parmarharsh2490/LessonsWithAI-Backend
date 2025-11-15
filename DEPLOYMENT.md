# Deployment Guide for Render

## Overview
This document describes the changes made to deploy the LessonsWithAI Spring Boot backend to Render.

## Changes Summary

### Dockerfile Improvements

#### Before:
```dockerfile
FROM gradle:8.10.2-jdk23 AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:23-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
```

#### After:
```dockerfile
FROM gradle:8.14-jdk21 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY gradle gradle/
COPY src src/
RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8081
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:${PORT:-8081}/actuator/health || exit 1
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8081} -jar app.jar"]
```

### Key Improvements:

1. **Java Version**: Upgraded to Java 21 LTS (from Java 23) for better stability and support
2. **Runtime Image**: Using JRE instead of JDK for ~50% smaller image size
3. **Security**: Application runs as non-root user
4. **Dynamic Port**: Supports Render's PORT environment variable
5. **Health Checks**: Configured for Render's health monitoring
6. **Memory Optimization**: JVM configured with 512MB max heap
7. **Build Optimization**: Selective file copying for better Docker layer caching

### Configuration Changes

#### build.gradle:
- Updated Java toolchain from 24 to 21
- Spring Boot version 3.3.6

#### settings.gradle:
- Added pluginManagement configuration
- Removed non-existent 'plugin' subproject

### New Files:

1. **.dockerignore**: Excludes unnecessary files from Docker build
2. **README.md**: Comprehensive documentation
3. **render.yaml**: Render deployment configuration
4. **DEPLOYMENT.md**: This deployment guide

## Deploying to Render

### Quick Start:

1. **Connect Repository**: Link your GitHub repository to Render

2. **Create Web Service**: 
   - Select "Web Service"
   - Choose your repository
   - Render will auto-detect the Dockerfile

3. **Configure Environment Variables**:
   ```
   PORT                          (auto-set by Render)
   SPRING_DATASOURCE_URL         jdbc:mysql://host:3306/lessonswithai
   SPRING_DATASOURCE_USERNAME    your_db_username
   SPRING_DATASOURCE_PASSWORD    your_db_password
   SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI    your_keycloak_url
   ```

4. **Deploy**: Render will build and deploy automatically

### Using render.yaml:

The included `render.yaml` file allows for infrastructure-as-code deployment:

```bash
# Simply commit render.yaml and push to trigger deployment
git add render.yaml
git commit -m "Add Render configuration"
git push
```

### Health Check:

Render will monitor the application health at:
```
/actuator/health
```

### Monitoring:

View logs in Render dashboard:
- Build logs: Shows Docker build progress
- Runtime logs: Shows application logs

## Environment Variable Mapping

Spring Boot automatically maps environment variables to properties:

| Environment Variable | Application Property |
|---------------------|---------------------|
| `SPRING_DATASOURCE_URL` | `spring.datasource.url` |
| `SPRING_DATASOURCE_USERNAME` | `spring.datasource.username` |
| `SPRING_DATASOURCE_PASSWORD` | `spring.datasource.password` |
| `SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI` | `spring.security.oauth2.resourceserver.jwt.issuer-uri` |

## Troubleshooting

### Build Fails:
- Check that all environment variables are set
- Verify database connectivity
- Check Render build logs

### Application Won't Start:
- Verify health check endpoint is accessible
- Check memory limits (default 512MB should be sufficient)
- Review application logs in Render dashboard

### Connection Issues:
- Ensure database allows connections from Render's IP ranges
- Verify Keycloak is accessible from Render
- Check security group/firewall rules

## Performance Tuning

### Memory:
Adjust JAVA_OPTS environment variable:
```
JAVA_OPTS=-Xmx768m -Xms384m
```

### Scaling:
Render supports horizontal scaling - add more instances in the dashboard.

## Support

For issues specific to:
- **Render**: https://render.com/docs
- **Spring Boot**: https://spring.io/guides
- **This Application**: Check repository issues
