package com.venkat.jaas.login1;

import com.venkat.jaas.login.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

/**
 * Created by venkatram.veerareddy on 8/31/2017.
 */

//https://www.javacodegeeks.com/2012/06/java-jaas-form-based-authentication.html

public class MyLoginModule implements LoginModule{

    private static final Logger LOGGER = LoggerFactory.getLogger(MyLoginModule.class);

    public static final String TEST_USER_NAME = "venkat";
    public static final String TEST_PASSWORD = "password";
    private CallbackHandler callbackHandler = null;
    boolean succeeded = false;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("User Name: ");
        callbacks[1] = new PasswordCallback("Password:", false);
        try {
            callbackHandler.handle(callbacks);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        String name = ((NameCallback) callbacks[0]).getName();
        String password = new String(((PasswordCallback) callbacks[1]).getPassword());
        if(TEST_USER_NAME.equals(name) && TEST_PASSWORD.equals(password)){
            LOGGER.info("Login is successfull.");
            succeeded = true;
            return succeeded;
        }else{
            LOGGER.info("Login is failed.");
            succeeded = false;
            throw new FailedLoginException("Authentication failed.");
        }
    }

    @Override
    public boolean commit() throws LoginException {
        return succeeded;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
