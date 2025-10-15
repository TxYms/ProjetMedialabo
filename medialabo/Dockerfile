### 1) Build Maven
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

### 2) Runtime JRE
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# ⚠️ adapte ce port selon le service :
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]