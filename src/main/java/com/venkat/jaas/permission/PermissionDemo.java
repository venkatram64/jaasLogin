package com.venkat.jaas.permission;

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
public class PermissionDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionDemo.class);
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.auth.login.config",
                    PermissionDemo.class.getResource("/login.config").toExternalForm());
            LoginContext loginContext = new LoginContext("MyLoginContext");
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
            //Action with access-control
            PrivilegedAction action = () -> {
                Permission permission = new MyPermission("test");
                AccessController.checkPermission(permission);
                LOGGER.info("Permission granted.");
                return null;
            };
           /* PrivilegedAction action = new PrivilegedAction() {
                @Override
                public Object run() {
                    Permission permission = new MyPermission("test");
                    AccessController.checkPermission(permission);
                    LOGGER.info("Permission granted.");
                    return null;
                }
            };*/
            subject.doAsPrivileged(subject, action, null);
        } catch (LoginException ex) {
            LOGGER.info("Authentication failed.");
        }
    }
}
