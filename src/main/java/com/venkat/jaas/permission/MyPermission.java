package com.venkat.jaas.permission;

import java.security.Permission;

/**
 * Created by Venkatram on 9/17/2017.
 */
public class MyPermission extends Permission{

    public MyPermission(String name){
        super(name);
    }
    @Override
    public boolean implies(Permission permission) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getActions() {
        return null;
    }
}
