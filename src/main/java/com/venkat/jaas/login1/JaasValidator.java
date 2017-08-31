package com.venkat.jaas.login1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Created by venkatram.veerareddy on 8/31/2017.
 */
public class JaasValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(JaasValidator.class);
    public static void main(String[] args){
        System.setProperty("java.security.auth.login.config", /*"jaaslogin.config"*/JaasValidator.class.getResource("/jaaslogin.config").toString());
        LoginContext loginContext = null;
        try{
            loginContext = new LoginContext("MyLogin", new MyCallbackHandler());
        }catch (Exception ex){
            ex.printStackTrace();
            LOGGER.info("Authentication failed.");
        }
        if(true){
            try {
                loginContext.login();
                LOGGER.info("Authentication is successful.");
               return;
            } catch (LoginException e) {
                e.printStackTrace();
                LOGGER.info("Authentication failed...");
                return;
            }

        }
    }
}
