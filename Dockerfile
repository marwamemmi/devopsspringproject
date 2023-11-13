FROM openjdk:11-alpine
WORKDIR /app
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run