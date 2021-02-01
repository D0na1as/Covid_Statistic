FROM openjdk:8
# copy the packaged jar file into our docker image
COPY build/libs/covid-0.0.1-SNAPSHOT.jar /covid.jar

# set to execute the jar
ENTRYPOINT ["java", "-jar", "covid.jar"]
