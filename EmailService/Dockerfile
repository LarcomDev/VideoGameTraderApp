FROM tomcat:8-jdk11-openjdk

ADD /target/EmailService-0.0.1-SNAPSHOT.jar emailservice.jar
ENTRYPOINT ["java", "-jar", "emailservice.jar"]