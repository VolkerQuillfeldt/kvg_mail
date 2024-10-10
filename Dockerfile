# Package stage
#
FROM openjdk:8-jdk-alpine
VOLUME [ "/tmp" ]
COPY /target/kvk_mailservice-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/urandom", "-jar", "app.jar" ]
