FROM eclipse-temurin:17-jdk-focal
 
WORKDIR /app
 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw
 
COPY src ./src
 
CMD ["./mvnw", "spring-boot:run"]