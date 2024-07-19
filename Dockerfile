FROM openjdk:17
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE=build/libs/mt-app.jar
ADD ${JAR_FILE} mt-app.jar
ENTRYPOINT ["java","-jar","/mt-app.jar"]