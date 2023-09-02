FROM openjdk:11
EXPOSE 8080
ADD ./target/my-demo-api-0.0.1-SNAPSHOT.jar my-demo-api.jar
ENTRYPOINT ["java", "-jar", "/my-demo-api.jar"]