FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/anpg-1.0-SNAPSHOT.jar /opt/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app.jar"]
