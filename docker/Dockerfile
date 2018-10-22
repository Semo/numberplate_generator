FROM alpine:edge

MAINTAINER Semo <semox78@gmail.com>

ENV SERVER_PORT 8000

RUN apk add --no-cache openjdk8
COPY files/UnlimitedJCEPolicyJDK8/* \
    /usr/lib/jvm/java-1.8-openjdk/jre/lib/security/

EXPOSE $SERVER_PORT

