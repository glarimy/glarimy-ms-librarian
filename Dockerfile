FROM openjdk:8-jdk-alpine
MAINTAINER Krishna Mohan Koyya <krishna@glarimy.com>
VOLUME /tmp
EXPOSE 2310
ARG JAR_FILE=target/glarimy-ms-librarian.jar
ADD ${JAR_FILE} glarimy-ms-librarian.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo:27017/library", "-Djava.security.egd=file:/dev/./urandom","-jar","/glarimy-ms-librarian.jar"]
