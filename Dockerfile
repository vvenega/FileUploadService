FROM adoptopenjdk/openjdk8
WORKDIR /
ARG FileUploadService-0.0.1-SNAPSHOT.jar
ADD FileUploadService-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8102
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]