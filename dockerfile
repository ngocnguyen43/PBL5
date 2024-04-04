FROM tomcat:10.1.20-jdk21-temurin-jammy
COPY ./target/ticketbox-0.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]