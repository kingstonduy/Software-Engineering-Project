FROM openjdk:17.0-slim
COPY petcare/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]