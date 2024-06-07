FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN ./mvnw
 
COPY src ./src
 
CMD ["./mvnw", "spring-boot:run"]