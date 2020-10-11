FROM docker.artifactory.a.intuit.com/oicp/jdk8
EXPOSE 8080

COPY target/tweet-craft-demo.jar tweet-craft-demo.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev","-jar","tweet-craft-demo.jar"]