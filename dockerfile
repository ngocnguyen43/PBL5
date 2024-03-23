FROM tomcat:jre17
COPY ./target/ticketbox-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/api.war