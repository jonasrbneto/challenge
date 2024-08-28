# Custom Java runtime using jlink in a multi-stage container build
FROM eclipse-temurin:21

ARG JAR_FILE=build/libs/challenge-0.0.4-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]