package com.venkat.jaas.custom;

import com.venkat.jaas.permission.MyPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.AccessController;
import java.security.Permission;
import java.security.Principal;
import java.security.PrivilegedAction;

/**
 * Created by Venkatram on 9/17/2017.
 */
public class CustomDemo {

        //http://www.avajava.com/tutorials/lessons/how-do-i-create-a-login-module.html
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomDemo.class);
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.auth.login.config",
                    CustomDemo.class.getResource("/custom-login.config").toExternalForm());
            LoginContext loginContext = new LoginContext("MyLoginContext", new MyCallbackHandler("user1","password"));
            loginContext.login();
            LOGGER.info("Authentication is successful.");
            //show user info
            Subject subject = loginContext.getSubject();
            LOGGER.info("User principals: ");
            for (Principal p : subject.getPrincipals()){
                LOGGER.info("\t" + p.toString());
            }
            LOGGER.info("User public credentials: ");
            for(Object pubCred: subject.getPublicCredentials()){
                LOGGER.info("\t" + pubCred.toString());
            }

            LOGGER.info("User private credentials: ");
            for(Object privCred: subject.getPrivateCredentials()){
                LOGGER.info("\t" + privCred.toString());
            }

        } catch (LoginException ex) {
            LOGGER.info("Authentication failed.");
        }
    }
}
