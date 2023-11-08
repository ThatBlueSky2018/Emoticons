FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./emoji-server/target/emo-verse.jar /app

CMD ["java", "-jar", "emo-verse.jar"]