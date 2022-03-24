FROM maven:3.6.3-openjdk-17 as builder
COPY ./webapp10/backend /code/
WORKDIR /code
#COPY /. /code/backend
COPY ./webapp10/backend/pom.xml /code/
RUN mvn package

FROM openjdk:17
WORKDIR /usr/src/app
COPY --from=builder code/target/*.jar /usr/src/app
EXPOSE 8443
CMD [ "java", "-jar", "Youdemy-0.0.1-SNAPSHOT.jar" ]

