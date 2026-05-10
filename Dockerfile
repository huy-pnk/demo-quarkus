## Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY gradlew gradlew.bat ./
COPY gradle ./gradle
COPY build.gradle settings.gradle gradle.properties ./
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon -q

COPY src ./src
RUN ./gradlew build -x test --no-daemon

## Run stage
FROM eclipse-temurin:21-jre
WORKDIR /deployments

COPY --from=build /app/build/quarkus-app/lib/ ./lib/
COPY --from=build /app/build/quarkus-app/*.jar ./
COPY --from=build /app/build/quarkus-app/app/ ./app/
COPY --from=build /app/build/quarkus-app/quarkus/ ./quarkus/

EXPOSE 8091

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar quarkus-run.jar"]
