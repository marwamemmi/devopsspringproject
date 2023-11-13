
FROM openjdk:11-jdk
WORKDIR /app
EXPOSE 8089
ADD target/DevOps_Project-2.1.jar /app/DevOps_Project-2.1.jar
CMD ["java", "-jar", "DevOps_Project-2.1.jar"]