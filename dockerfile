FROM tomcat:10.0.27-jre17-temurin-focal
COPY ./target/ticketbox.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]