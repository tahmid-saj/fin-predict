# dockerhub repo:
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# render autodeploy:
# FROM ubuntu:latest AS build
# RUN apt-get update
# RUN apt-get install openjdk-17-jdk -y
# COPY . .
# RUN chmod +x ./mvnw
# RUN ./mvnw dependency:go-offline
 
# COPY src ./src

# EXPOSE 8080

# CMD ["./mvnw", "spring-boot:run"]

# docker tag local-image:tagname new-repo:tagname
# docker push new-repo:tagname