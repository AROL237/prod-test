#FROM eclipse-temurin:17-jdk-jammy
#WORKDIR /app
#
#COPY .mvn .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src
#CMD ["./mvnw","spring-boot:run"]


FROM openjdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]