FROM openjdk:11-jdk-slim-sid
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} product-ms.jar
ENTRYPOINT ["java", "-jar", "product-ms.jar"]