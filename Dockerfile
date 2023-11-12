
FROM marwamemmi/alpine:1.0.0
RUN apk add openjdk11
EXPOSE 8081
COPY target/DevOps_Project-2.1.jar /app/DevOps_Project-2.1.jar
CMD ["java", "-jar", "/app/DevOps_Project-2.1.jar"]