FROM openjdk:8
ADD target/restful-web-services-0.0.1-SNAPSHOT.jar restful-web-services.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","restful-web-services.jar"]
