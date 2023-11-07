
FROM marwamemmi/alpine:1.0.0
RUN apk add openjdk11
WORKDIR /spring-app
EXPOSE 80
CMD "java"