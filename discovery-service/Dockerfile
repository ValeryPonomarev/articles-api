FROM maven:3.6.1-jdk-11
MAINTAINER Valery Ponomarev <lord.pvi@gmail.com>

ADD ./target/discovery-service.jar /app/discovery-service.jar

CMD java -Xmx200m -jar /app/discovery-service.jar
EXPOSE 8761