#  EJB REMOTE SAMPLE

This repository has an example of how to create an EJB remote and expose it using JBoss EAP/Wildfly. It is composed by these components:

 -**sampleejbservice**: EJB remote service that expose a remote interface and has a unit test to test them.

## BUILDING PROJECT

To build project go to $HOME_PROJECT and execute this Maven command:

    mvn clean compile -DskipTests

Note the `-DskipTests` property is used, because it expect the EJB remote service is already deployed and available at http://localhost:8080.

## DEPLOYING IN THE JBOSS EAP/WILDFLY


## TESTING STANDALONE JAVA APPLICATION APPROACH


##  TESTING SERVER TO SERVER APPROACH
