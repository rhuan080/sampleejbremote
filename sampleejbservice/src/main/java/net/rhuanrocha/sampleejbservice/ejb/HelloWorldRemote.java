package net.rhuanrocha.sampleejbservice.ejb;

import javax.ejb.Remote;
import java.io.Serializable;

@Remote
public interface HelloWorldRemote extends Serializable {

    public String sayHello(String username);
}
