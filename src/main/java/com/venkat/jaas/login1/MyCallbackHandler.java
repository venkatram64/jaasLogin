package com.venkat.jaas.login1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by venkatram.veerareddy on 8/31/2017.
 */
public class MyCallbackHandler implements CallbackHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCallbackHandler.class);
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        for(Callback cb: callbacks){
            if(cb instanceof NameCallback){
                NameCallback ncb = (NameCallback)cb;
                //Display prompt
                System.out.println(ncb.getPrompt());
                //Read password
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                ncb.setName(bufferedReader.readLine());
            }
            else if(cb instanceof PasswordCallback){
                PasswordCallback pcb = (PasswordCallback)cb;
                //Display prompt
                System.out.println(pcb.getPrompt());
                //Read password
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                pcb.setPassword(bufferedReader.readLine().toCharArray());
            }
        }

    }
}
