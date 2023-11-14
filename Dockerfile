FROM maven:3.8.2-openjdk-11
WORKDIR /app
COPY . .
RUN mvn clean install
