# ── Etapa 1: compilar con Maven ─────────────────────────────
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copiar dependencias primero (cache de capas)
COPY pom.xml .
RUN apk add --no-cache maven && mvn dependency:go-offline -q

# Copiar código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests -q

# ── Etapa 2: imagen final liviana (solo JRE) ─────────────────
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/sistema-inscripcion-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
