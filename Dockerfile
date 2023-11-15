FROM marwamemmi/alpine:1.0.0
RUN apk add openjdk11
EXPOSE 8082
ADD ./target/DevOps_Project-2.1.jar DevOps_Project-2.1.jar
ENTRYPOINT [ "java", "-jar" ,"DevOps_Project-2.1.jar" ]
