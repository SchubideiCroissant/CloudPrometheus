FROM openjdk:17-jdk-slim

WORKDIR /app

# Kopiere dein eigenes JAR
COPY target/Rest-Demo-1.0-SNAPSHOT.jar app.jar

# Kopiere alle Abhängigkeiten
COPY target/dependency/ ./dependency/

EXPOSE 8080

# Starte mit vollständigem Classpath (alle JARs)
CMD ["java", "-cp", "app.jar:dependency/*", "de.home.vs.rest.service.RestService"]
