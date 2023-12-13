FROM openjdk:17-jdk-alpine

WORKDIR /usr/src

COPY ./emoji-server/target/emo-verse.jar /usr/src

EXPOSE 8080

CMD ["java", "-jar", "emo-verse.jar"]