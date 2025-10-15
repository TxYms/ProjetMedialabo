### 1) Build Maven
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Cache des deps
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Code
COPY src ./src

# Build
RUN mvn -q -DskipTests package

# === Vérification: les templates DOIVENT être dans le JAR ===
# Si cette ligne ne trouve rien, le build échoue -> à corriger côté projet.
RUN jar tf target/*.jar | grep -i 'BOOT-INF/classes/templates/'


### 2) Runtime (JDK pour pouvoir vérifier/diagnostiquer)
FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Port HTTP du front
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]