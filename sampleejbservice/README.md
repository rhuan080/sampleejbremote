#  EJB REMOTE SAMPLE

This repository has an example of how to create an EJB remote and expose it using JBoss EAP/Wildfly. It is composed by these components:

 -**sampleejbservice**: EJB remote service that expose a remote interface and has a unit test to test them.
 
## EJB SERVICE

### BUILDING PROJECT

To build project go to `$HOME_SAMPLEEJBSERVICE` and execute this Maven command:

    mvn clean package -DskipTests

Note the `-DskipTests` property is used, because it expect the EJB remote service is already deployed and available at http://localhost:8080.

### DEPLOYING IN THE JBOSS EAP/WILDFLY

Copy the war file to `$HOME_WILDFLY/standalone/deployments` . 

    cp $HOME_SAMPLEEJBSERVICE/target/sampleejbservice-1.0-SNAPSHOT.war .$HOME_WILDFLY/standalone/deployments/
Note that the application will print the JNDIs in log file and log console.

	java:global/sampleejbservice-1.0-SNAPSHOT/HelloWorld!net.rhuanrocha.sampleejbservice.ejb.HelloWorldRemote
	java:app/sampleejbservice-1.0-SNAPSHOT/HelloWorld!net.rhuanrocha.sampleejbservice.ejb.HelloWorldRemote
	java:module/HelloWorld!net.rhuanrocha.sampleejbservice.ejb.HelloWorldRemote
	java:jboss/exported/sampleejbservice-1.0-SNAPSHOT/HelloWorld!net.rhuanrocha.sampleejbservice.ejb.HelloWorldRemote
	ejb:sampleejbservice-1.0-SNAPSHOT/HelloWorld!net.rhuanrocha.sampleejbservice.ejb.HelloWorldRemote
	java:global/sampleejbservice-1.0-SNAPSHOT/HelloWorld
	java:app/sampleejbservice-1.0-SNAPSHOT/HelloWorld
	java:module/HelloWorld

## EJB CLIENT

### TESTING STANDALONE JAVA APPLICATION APPROACH

This is an unit test that connect to EJB remote deployed in the step above, call a function passing a parameter and receive a response from EJB remote. Note that to run this test you should be already deployed the war. This test has two type of parameters, one can be passed as JVM parameter e other that is static parameter. These are the JVM parameter that can be passed when running this test:

|Parameter| Description | Default Value|
|--|--|--|
| net.rhuanrocha.callsize | Number of times the EJB remote is called | 1            |   
| net.rhuanrocha.cacheejb | Cache the EJB and reuse to all calls | true |
|net.rhuanrocha.jndi|JNDI of remote EJB|ejb:/sampleejbservice-1.0-SNAPSHOT/HelloWorld!net.rhuanrocha.sampleejbservice.ejb.HelloWorldRemote|
|net.rhuanrocha.sayhelloinput|Parameter to be passed to remote EJB method| Anonymous|

To test we should execute the Maven command above passing the parameters that we want to configure.

    mvn verify -Dnet.rhuanrocha.callsize={number}

Note that we should replace the {number} to some number.


###  TESTING SERVER TO SERVER APPROACH 
