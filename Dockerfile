# syntax=docker/dockerfile:1

FROM adoptopenjdk/openjdk11 as BUILD

WORKDIR /build

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src/ ./src

RUN ./mvnw clean package

FROM adoptopenjdk/openjdk13:alpine-jre

WORKDIR /app

COPY --from=BUILD /build/target/library-ms-members-1.0.2.jar ./library-ms-members-1.0.2.jar

CMD ["java", "-jar", "./library-ms-members-1.0.2.jar"]
