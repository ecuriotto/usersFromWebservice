## Context
This programs queries the server at URL "https://reqres.in/api/users?page=1;". 
It retrieves a json-encoded list of users, decoded them using the `gson` library and displays it on the command line.

## Requirements
Minimum Java version required: Java 11
Tested with Maven 3.6.3 and java 11

## Compiling testing and running the program
```
mvn compile
mvn test
mvn exec:java -Dexec.mainClass="org.enrico.code.UserClient" -Dexec.cleanupDaemonThreads=false
```

## Steps:
mvn compile
mvn test
mvn exec:java -Dexec.mainClass="org.enrico.code.controller.UserClient" -Dexec.cleanupDaemonThreads=false

## Expected results
```
George - Bluth
Janet - Weaver
Emma - Wong
Eve - Holt
Charles - Morris
Tracey - Ramos
```
