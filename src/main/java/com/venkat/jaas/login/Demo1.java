package com.venkat.jaas.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Created by venkatram.veerareddy on 8/31/2017.
 */

//http://mschrag.blogspot.in/2008/03/jaas-kerberos-auth.html
public class Demo1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo1.class);


    public static void main(String[] args){
        try{
            System.setProperty("java.security.auth.login.config", Demo1.class.getResource("/login.config").toExternalForm());
            LoginContext loginContext = new LoginContext("MyLoginContext");
            loginContext.login();
            LOGGER.info("Authentication is successful.");
        }catch(LoginException ex){
            LOGGER.info("Authentication failed.");
        }
    }
}
