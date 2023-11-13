FROM openjdk:17-jdk
WORKDIR /app
COPY target/DevOps_Project-2.1.jar /app/DevOps_Project-2.1.jar
EXPOSE 8086
CMD ["java", "-jar", "DevOps_Project-2.1.jar"]