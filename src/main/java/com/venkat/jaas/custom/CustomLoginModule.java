package com.venkat.jaas.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

/**
 * Created by venkatram.veerareddy on 8/31/2017.
 */

public class CustomLoginModule implements LoginModule{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomLoginModule.class);

    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;

    private boolean debug = false;

    private boolean succeeded = false;
    private boolean commitSucceeded = false;

    private String username;
    private char[] password;

    private UserPrincipal userPrincipal;
    private PasswordPrincipal passwordPrincipal;




    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
        if(callbackHandler == null){
            throw new LoginException("Error: No CallbackHandler available ");
        }
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("username");
        callbacks[1] = new PasswordCallback("password", false);
        try{
            callbackHandler.handle(callbacks);
            username = ((NameCallback)callbacks[0]).getName();
            password = ((PasswordCallback)callbacks[1]).getPassword();
            if(username == null || password == null){
                LOGGER.error("Callback handler does not return login data properly");
                throw new LoginException("Callback handler does not return login data properly");
            }
            if(username.equals("user1") && password.equals("password")){
                succeeded = true;
                return succeeded;
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (UnsupportedCallbackException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        if(succeeded == false){
            return succeeded;
        }else{
            userPrincipal = new UserPrincipal(username);
            if(!subject.getPrincipals().contains(userPrincipal)){
                subject.getPrincipals().add(userPrincipal);
                LOGGER.debug("User principal added: " + userPrincipal);
            }
            passwordPrincipal = new PasswordPrincipal(new String(password));
            if(!subject.getPrincipals().contains(passwordPrincipal)){
                subject.getPrincipals().add(passwordPrincipal);
                LOGGER.debug("Password principal added: " + passwordPrincipal);
            }

            commitSucceeded = true;
        }
        return commitSucceeded;
    }

    @Override
    public boolean abort() throws LoginException {

        if(succeeded == false){
            return succeeded;
        }else if(succeeded == true && commitSucceeded == false){
            succeeded = false;
            username = null;
            if(password != null){
                password = null;
            }
            userPrincipal = null;
        }else{
            logout();
        }
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        succeeded = false;
        succeeded = commitSucceeded;
        username = null;
        if(password != null){
            for(int i = 0; i < password.length; i++){
                password[i] = ' ';
                password = null;
            }
        }
        userPrincipal = null;
        return true;
    }

}
