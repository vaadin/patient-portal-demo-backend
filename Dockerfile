FROM openjdk:8-jdk-alpine
ARG datasourceUrl=localhost:3306
ARG jpaAuto=none
ENV JAVA_OPTS="-Dspring.datasource.url=jdbc:mysql://$datasourceUrl/pp -Dspring.jpa.hibernate.ddl-auto=$jpaAuto"
COPY rest-and-angular-ui-specific-services/target/rest-and-angular-ui-specific-services-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]