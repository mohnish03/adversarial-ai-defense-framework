FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY . .
COPY backend/target/llm-defense-api-1.0.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
