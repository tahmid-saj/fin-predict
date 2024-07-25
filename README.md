# [financeprediction.io](http://financeprediction.io/)
<br>

Finance prediction app to view historical and current market data, weekly predictions on BTC / stocks / forex, and get support from AI driven services. Developed with Spring Boot, Maven, Thymeleaf, Django, AWS, Docker, MySQL.
<br>
<br>

<figure>
  <img width="946" alt="image" src="https://github.com/user-attachments/assets/24c51268-c37a-46fd-981d-f1df7a9fc866">
</figure>
<br>

[Figure 1: High level view](https://whimsical.com/fin-predict-9UPaQyGHkRt5NYyrn1LJh5)
<br>
<br>

### The application consists of the following main components:

1. __Client__: Springboot frontend web application which sends requests to the __Finance Predict API__. Currently the frontend is deployed using Docker on GCP App Engine.
2. __Finance Predict API__: Finance prediction API developed using django and deployed to AWS EC2. API serves requests for finance prediction, market data queries and chatbot queries.
3. __Services__: The following services are used:
* OpenAI: Services chatbot requests
* polygon.io: Provides market data for requests from the __Finance Predict API__
5. __Machine learning__: ML models for BTC and stock predictions developed using TensorFlow / PyTorch. [ml-job-scheduler](https://github.com/tahmid-saj/ml-job-scheduler) handles the automated job runs from preprocessing, training, predictions, postprocessing, etc. The logs of the job runs are stored in S3 and the data is stored in MongoDB.
6. __Databases__: ML prediction data is stored within MongoDB.
7. __Data engineering__: Performs manual data migration using an external ETL / ELT API developed in Go, [etl-elt-api](https://github.com/tahmid-saj/etl-elt-api)
8. __Security__: AWS security services (AWS Inspector and GuardDuty) which monitors the security of APIs. Later, data and logs from the services are queried and viewed using an external security tool.
9. __Monitoring__: Monitoring service which collects data and logs from APIs using CloudWatch, then stores them in S3 to be viewed as a dashboard via an external monitoring tool. 
10. __Notifications__: Receives various data and logs in S3 buckets and later sends emails (on issues or failures) using SQS and Lambda.

### Setting up the development environment:

1. __Cloning the repository__: You would first need to clone this repository on the host you want to set up your development environment:
```shell
git clone https://github.com/tahmid-saj/fin-predict.git
``` 
2. __Installing dependencies__: The maven dependencies are provided in the __pom.xml__ file. They wil only need to be installed using a valid IDE such as IntelliJ IDEA or NetBeans.
3. __API__: Client requests to go a the __Finance Predict API__ which provides the following:
* Finance prediction
* Historic market data
* Chatbot responses
4. __Services__: API keys are used from the following services in the __Finance Predict API__:
* OpenAI: Services chatbot requests
* polygon.io: Provides market data for requests from the __Finance Predict API__
5. __Databases__: A MongoDB cluster and collections are created, and a connection is established with the __Finance Predict API__.
6. __Data engineering__: The set up for the development environment for the data engineering tool can be found [here](https://github.com/tahmid-saj/etl-elt-api).
7. __Machine learning__: The set up for the development environment for the ML job scheduler can be found [here](https://github.com/tahmid-saj/ml-job-scheduler).
8. __AWS__: Setting up the AWS services is an optional step as this is on a development environment. However, the same services could be used to create the tools mentioned in the high level view.
9. __Running the client__: The client can be run using an IDE or Docker using:
* To package your application with Maven run:
```shell
mvn clean package
```
* Test the jar file:
```shell
java -jar target/<SNAPSHOT NAME>.jar
```
* Build the docker image:
```shell
docker build -t <PROJECT NAME> .
```
* Tag the docker image:
```shell
docker tag <PROJECT NAME> <YOUR USERNAME>/<PROJECT NAME>
```
* Push the docker image to Docker Hub:
```shell
docker push <YOUR USERNAME>/<PROJECT NAME>:<TAG NAME>
```
After the image is pushed, it can be used in GCP App Engine or with a docker container in the development environment.
