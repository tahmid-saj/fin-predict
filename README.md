# [financeprediction.io](http://financeprediction.io/)
<br>

Finance prediction app to view historical and current market data, weekly predictions on BTC / stocks / forex, and get support from AI driven services. Developed with Spring Boot, Maven, Thymeleaf, Django, AWS, Docker, MySQL.
<br>
<br>

<figure>
  <img width="927" alt="image" src="https://github.com/user-attachments/assets/3e052a4b-9faa-4bf4-a33c-079843a5942a">
</figure>
<br>

[Figure 1: High level view](https://whimsical.com/fin-predict-9UPaQyGHkRt5NYyrn1LJh5)
<br>
<br>

### The application consists of the following main components:

1. __Client__:
2. __Finance Predict API__:
3. __Services__:
4. __Machine learning__:
5. __Databases__:
6. __Data engineering__:
7. __Security__: AWS security services (AWS Inspector and GuardDuty) which monitors the security of APIs. Later, data and logs from the services are queried and viewed using an external security tool.
8. __Monitoring__: Monitoring service which collects data and logs from APIs using CloudWatch, then stores them in S3 to be viewed as a dashboard via an external monitoring tool. 
9. __Notifications__: Receives various data and logs in S3 buckets and later sends emails (on issues or failures) using SQS and Lambda.

### Setting up the development environment:

1. __Cloning the repository__:
2. __Installing dependencies__:
3. __Environment variables__:
4. __API__:
5. __Services__:
6. __Databases__:
7. __Data engineering__:
8. __Machine learning__:
9. __AWS__:
10. __Running the client__:
