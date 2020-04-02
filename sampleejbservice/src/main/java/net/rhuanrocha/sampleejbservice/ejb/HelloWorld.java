package net.rhuanrocha.sampleejbservice.ejb;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Stateless
public class HelloWorld implements HelloWorldRemote {
    @Override
    public String sayHello(String username) {
        return "Hello "+ username;
    }


}
