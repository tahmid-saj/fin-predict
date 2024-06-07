FROM eclipse-temurin:17-jdk-focal
WORKDIR /app

COPY . .
RUN ./mvnw
 
COPY src ./src
 
CMD ["./mvnw", "spring-boot:run"]