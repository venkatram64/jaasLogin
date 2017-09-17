package com.venkat.jaas.custom;

import javax.security.auth.Subject;
import java.io.Serializable;
import java.security.Principal;

/**
 * Created by venkatram.veerareddy on 8/31/2017.
 */

public class UserPrincipal implements Principal, Serializable{

    private String name;

    public UserPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserPrincipal other = (UserPrincipal) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "UserPrincipal [name=" + name + "]";
    }
}
