#################################################
# Imagen para el contenedor de compilación
#################################################

FROM maven:3.6.3-openjdk-17 as builder

# Copia el código del proyecto
COPY ./webapp10/backend /code/

# Define el directorio de trabajo donde ejecutar comandos
WORKDIR /code

# Copia las dependencias del proyecto
COPY ./webapp10/backend/pom.xml /code/

# Compila proyecto
RUN mvn package

#################################################
# Imagen para el contenedor de la aplicación
#################################################

FROM openjdk:17

# Define el directorio de trabajo, donde se esta el JAR
WORKDIR /usr/src/app

# Copia el JAR del contenedor de compilación
COPY --from=builder code/target/*.jar /usr/src/app

# Indica el puerto que expone el contenedor
EXPOSE 8443

# Comando que se ejecuta al hacer docker run
CMD [ "java", "-jar", "Youdemy-0.0.1-SNAPSHOT.jar" ]

