# FROM maven:3.9-eclipse-temurin-21 AS builder
# VOLUME /tmp
# COPY target/*.jar app.jar
# COPY src src
# WORKDIR /app
# COPY --from=builder app/dependencies/ ./
# COPY --from=builder app/spring-boot-loader/ ./
# COPY --from=builder app/snapshot-dependencies/ ./
# COPY --from=builder app/application/ ./
# ENTRYPOINT ["java","-jar","/app.jar"]


# Builder Stage
FROM maven:3.9-eclipse-temurin-21 AS builder
VOLUME /tmp
COPY target/*.jar app.jar
COPY src src
WORKDIR /app
COPY  pom.xml .

# Runtime Stage
FROM builder AS runtime
WORKDIR /app
ENTRYPOINT ["java","-jar","/app.jar"]

