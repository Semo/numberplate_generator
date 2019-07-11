FROM adoptopenjdk/openjdk11:x86_64-debian-jre-11.0.3_7
LABEL maintainer="semox78@gmail.com"
# RUN apt update && apt install -y fontconfig
# RUN apt install openjdk-11-jdk -y
# RUN mkdir -p /usr/share/fonts/truetype/europlate
# COPY src/main/resources/EuroPlate.ttf /usr/share/fonts/truetype/europlate
# RUN fc-cache -f -v
#VOLUME /tmp
ADD target/anpg-1.0-SNAPSHOT.jar /opt/app.jar
ENTRYPOINT ["java", "-Dsun.java2d.debugfonts=false", "-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app.jar"]