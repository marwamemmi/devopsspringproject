
FROM openjdk:11-alpine
WORKDIR /app
COPY target/DevOps_Project-2.1.jar /app/DevOps_Project-2.1.jar
EXPOSE 8089
CMD ["java", "-jar", "DevOps_Project-2.1.jar"]
