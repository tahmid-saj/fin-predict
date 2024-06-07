FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
 
COPY src ./src

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]