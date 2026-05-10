# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Development with live reload
./gradlew quarkusDev        # runs at http://localhost:8080, dev UI at /q/dev/

# Build & test
./gradlew build             # compiles, runs tests, produces build/quarkus-app/quarkus-run.jar
./gradlew test              # unit tests only

# Run the built JAR
java -jar build/quarkus-app/quarkus-run.jar

# Uber JAR (fat jar, single file)
./gradlew build -Dquarkus.package.jar.type=uber-jar
java -jar build/*-runner.jar

# Native executable (requires GraalVM)
./gradlew build -Dquarkus.native.enabled=true

# Native build via container (no GraalVM required locally)
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

## Architecture

**Stack:** Quarkus 3.35.2, Java 25, Gradle, Jakarta REST, Quarkus ARC (CDI)

**Main package:** `org.test`

This is a minimal REST API starter with a single resource:

- `src/main/java/org/test/GreetingResource.java` — `GET /hello` returns `"Hello from Quarkus REST"`
- `src/main/resources/application.properties` — empty; all config is Quarkus defaults
- `src/test/java/org/test/GreetingResourceTest.java` — `@QuarkusTest` with RestAssured
- `src/native-test/java/org/test/GreetingResourceIT.java` — `@QuarkusIntegrationTest` for packaged/native mode

## Docker

```bash
# JVM image
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/code-with-quarkus-jvm .
docker run -i --rm -p 8080:8080 quarkus/code-with-quarkus-jvm

# Native micro image
docker build -f src/main/docker/Dockerfile.native-micro -t quarkus/code-with-quarkus .
docker run -i --rm -p 8080:8080 quarkus/code-with-quarkus
```
