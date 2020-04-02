package net.rhuanrocha.sampleejbservice.ejb;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;

public class HelloWorldTest {

    private static Logger logger = Logger.getLogger(HelloWorldTest.class.getName());
    private Optional<String> callsize;
    private Optional<String> cacheejb;
    private Optional<String> jndi;
    private Optional<String> sayhelloinput;

    @Before
    public void initTestConfigurations(){
        callsize = Optional.ofNullable(System.getProperty("net.rhuanrocha.callsize"));
        cacheejb = Optional.ofNullable(System.getProperty("net.rhuanrocha.cacheejb"));
        jndi = Optional.ofNullable(System.getProperty("net.rhuanrocha.jndi"));
        sayhelloinput = Optional.ofNullable(System.getProperty("net.rhuanrocha.sayhelloinput"));

    }

    @Test
    public void testEjbRemote() throws NamingException {

        HelloWorldRemote worldRemote = null;
        Context context = new InitialContext(getEJBProperties());
        boolean isCacheejb = Boolean.parseBoolean(cacheejb.orElse("true"));

        for(int i = Integer.parseInt(callsize.orElse("1"))-1; i >= 0; i-- ) {

            if(!isCacheejb || worldRemote == null ) {
                logger.info("Looking up EJB...");
                worldRemote = (HelloWorldRemote) context
                        .lookup(jndi.orElse("ejb:/sampleejbservice-1.0-SNAPSHOT/HelloWorld!net.rhuanrocha.sampleejbservice.ejb.HelloWorldRemote"));
            }
            logger.info("Calling EJB...");
            Assert.assertEquals(worldRemote.sayHello(sayhelloinput.orElse("Anonymous")), "Hello "+sayhelloinput.orElse("Anonymous"));

        }
    }

    private Properties getEJBProperties(){
        Properties jndiProps = new Properties();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        jndiProps.put(Context.SECURITY_PRINCIPAL, "ejbuser");
        //jndiProps.put(Context.SECURITY_CREDENTIALS, "redhat1!");
        return jndiProps;
    }
}
