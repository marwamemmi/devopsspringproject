
FROM marwamemmi/alpine:1.0.0
RUN apk add openjdk11
EXPOSE 80
CMD "java"