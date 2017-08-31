package com.venkat.jaas.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Created by venkatram.veerareddy on 8/31/2017.
 */

//http://mschrag.blogspot.in/2008/03/jaas-kerberos-auth.html
    //http://sorcersoft.org/resources/jini-security/Jini_Security_Revisited.pdf
public class MyDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyDemo.class);


    public static void main(String[] args){
        try{
            System.setProperty("java.security.auth.login.config", MyDemo.class.getResource("/login-ks.config").toExternalForm());
            LoginContext loginContext = new LoginContext("MyLoginContext", new MyCallbackHandler());
            loginContext.login();
            LOGGER.info("Authentication is successful.");
        }catch(LoginException ex){
            LOGGER.info("Authentication failed.");
        }
    }
}
