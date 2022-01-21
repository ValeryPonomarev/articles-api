FROM maven:3.6.1-jdk-11
MAINTAINER Valery Ponomarev <lord.pvi@gmail.com>

ADD ./target/articles.jar /app/articles.jar

CMD java -Xmx200m -jar /app/articles.jar
EXPOSE 8080