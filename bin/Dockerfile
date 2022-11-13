FROM openjdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9955
ENTRYPOINT ["java","-jar","app.jar"]