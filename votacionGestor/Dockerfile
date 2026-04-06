FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/votacion-gestor.jar app.jar

EXPOSE 8443

ENTRYPOINT ["java", "-jar", "app.jar"]