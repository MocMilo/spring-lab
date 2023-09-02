# Spring-lab project
This is Spring boot project for experiments and POC.

### 1) project contains examples of:
- simple db model
- JPA repository with Postgres DB (Docker container)
- JMS producer and consumer with Kafka JMS (Docker container)
- simple REST controller http://localhost:8080/test
- simple Unit tests config

### 2) To run application on localhost:
1. Open as Maven project in IntelliJ
2. Go to main project directory and run:
```
$ docker-compose up -d
```
3. Open DBeaver and connect to database (Postgres driver)
```
host:localhost
database:springlab
port:5432
user:root
pass:root
```

4. Run script with in \sql folder to create DB tables
5. Run Project as Spring boot application in IntelliJ

### 3) To test working POC:
just post request to url:
```
http://localhost:8080/test
```
#### For each request check changes:
1. In database "Department" table new row should be created.
2. In console you should see JMS info about created objects.

### 4) To check health with Actuator
```
http://localhost:9001/actuator/metrics

http://localhost:9001/actuator/metrics/executor.completed
```
