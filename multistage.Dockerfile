FROM node:latest as angular-build
COPY ./webapp10/frontend /code/frontend
WORKDIR /code/frontend
RUN npm install
RUN npx ng build --prod --base-href="/new/"

FROM maven:3.6.3-openjdk-17 as builder
COPY ./webapp10/backend /code/
COPY --from=angular-build ./code/frontend/dist ../backend/src/main/resources/static/new
WORKDIR /code
COPY ./webapp10/backend/pom.xml /code/
RUN mvn package

FROM openjdk:17
WORKDIR /usr/src/app
EXPOSE 8443
COPY --from=builder code/target/*.jar /usr/src/app
CMD [ "java", "-jar", "Youdemy-0.0.1-SNAPSHOT.jar" ]

