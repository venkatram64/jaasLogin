package com.venkat.jaas.db;

import com.venkat.jaas.custom.PasswordPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.*;
import java.io.IOException;

/**
 * Created by venkatram.veerareddy on 9/1/2017.
 */
public class DBCallbackHandler implements CallbackHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(DBCallbackHandler.class);

    private String username = null;
    private String password = null;

    public DBCallbackHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        LOGGER.info("Callback Handler invoked ");
        for(int i = 0; i < callbacks.length; i++){
            if(callbacks[i] instanceof NameCallback){
                NameCallback nameCallback = (NameCallback) callbacks[i];
                nameCallback.setName(username);
            }else if(callbacks[i] instanceof PasswordCallback){
                PasswordCallback passwordCallback = (PasswordCallback) callbacks[i];
                passwordCallback.setPassword(password.toCharArray());
            }else{
                throw new UnsupportedCallbackException(callbacks[i], "The submitted Callback is unsupported.");
            }
        }

    }
}
