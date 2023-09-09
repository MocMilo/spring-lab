## Spring-lab project
### This is Spring boot project for experiments and POC proof-of-concept. 
#### Use JDK 17

### 1) project contains examples:
- data model + repositories
- rest controllers
- Kafka producer and consumer
- cache with Redis
- parametrized unit tests with: mockmvc, h2 Sql scripts
- JVM and GC memory management experiment
- actuator
- logback
- aspect custom adnotation
- lombok
- OAuth with Keycloak

#### Project components (docker-compose):
- Postgres DB
- Kafka
- Redis + Redis Commander
- Prometheus
- Grafana
- Logstash
- Elastic
- Kibana
- Keycloak + dedicated Postgres DB

### POSTMAN
In `/postman` folder are stored *environment* and *collection* settings ready to test experiments and proof-of-concepts.

**Note:** *For security testing `client_secret` and `access_token` have to be obtained from running Keycloak instance.*

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

4. Run script with in `\sql` folder to create DB tables
5. Run Project as Spring boot application in IntelliJ

### 3) To run POC:
just post request to url (param is number of experiment to execute):
```
http://localhost:8080/experiment/1
```
Check results in **Grafana** or **VisualVM** with **VisualGC** plugin.

### 4) Redis in-memory database
url to test cache (first request is fetch from db, next requests from Redis cache)
```
http://localhost:8080/employees
```
url to Redis Commander: 
```
http://localhost:8081
```

In case of change in Redis serialization config flush Redis with command:
```
FLUSHDB
```
To remove all cached data
```
FLUSHALL
```
### 5) To check health with Actuator (for debug purpose)
```
http://localhost:9001/actuator/metrics

http://localhost:9001/actuator/metrics/executor.completed
```
### 6) Prometheus UI (for debug purpose, recommended use: Grafana)
```
http://localhost:9090
```
check example graph with example query:
jvm_memory_used_bytes{area="heap"}

### 7) Grafana UI
```
http://localhost:3000
```
login with default user: Admin

Go to location in Grafana menu: 
***Home > Connections > Data sources > Add data source***

choose **> Prometheus**
- add Prometheus URL: http://localhost:9090
- add Dashboard
- add visualisations

***Home > Dashboards > New Dashboard > Edit panel***
- Query
- Add example metric jvm_buffer_memory_used_bytes

### 8) Kibana Elastic UI
```
http://localhost:5601
```
In Kibana UI use source in name pattern: 
#### logstash-create_date

### 9) OAuth security with Keycloak (no SSL configured!)
**Note:** *Keycloak uses dedicated Postgres db in Docker container, encapsulated in separate Docker network
(to avoid possible conflicts with  springlab-db container in Springboot autoconfiguration)*

*To access Keycloak console:*
```
http://localhost:8082/
```
```
user: admin
pass: Keycloak1234
```

```
First use of Keycloak (realm master):
- menu Clients: create client with client id "spring-lab-app"
- menu Clients: Capability Config set: Client authentication:ON, Authorization:ON, Standard flow:true, Direct access grants:true
- menu Realm roles: create role SYS_ADMIN
- menu Users: add user role to existing admin user
- menu Realm settings: Require SSL: none (not for production!)

note: in menu Clients (choose current client) in tab credentials is sotred client_secret
```


For testing prof-of-concepts go to `/postman` folder and import environment and collections settings stored there.

*To get access_token:*
```
http://localhost:8082/realms/master/protocol/openid-connect/token
```
*example secured endpoint:*
```
http://localhost:8080/secured/data
```
