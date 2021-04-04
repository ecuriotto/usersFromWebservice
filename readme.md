Java program to get a list of users from an external webservice.
Tested with Maven 3.6.3 and java 11
Minimum Java version required: Java 11
Steps:
mvn compile
mvn test
mvn exec:java -Dexec.mainClass="org.enrico.code.controller.UserClient" -Dexec.cleanupDaemonThreads=false
It should return: 
George - Bluth
Janet - Weaver
Emma - Wong
Eve - Holt
Charles - Morris
Tracey - Ramos

