FROM openjdk:11-jdk-slim
WORKDIR /app
COPY build/libs/hiv2.jar hiv2.jar
EXPOSE 8080
CMD ["java", "-jar", "hiv2.jar"]