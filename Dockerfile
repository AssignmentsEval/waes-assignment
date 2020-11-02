FROM openjdk:11.0.3-jre-slim

WORKDIR /code

ADD target/*.jar waes-assignment.jar

EXPOSE 8080

ENTRYPOINT exec java \
    -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} \
    -jar /code/waes-assignment.jar
