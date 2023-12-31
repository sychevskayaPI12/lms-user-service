/*
 * This file is generated by jOOQ.
 */
package com.anast.lms.generated.jooq.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserPassword implements Serializable {

    private static final long serialVersionUID = 1511955457;

    private Integer id;
    private String  login;
    private String  password;

    public UserPassword() {}

    public UserPassword(UserPassword value) {
        this.id = value.id;
        this.login = value.login;
        this.password = value.password;
    }

    public UserPassword(
        Integer id,
        String  login,
        String  password
    ) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UserPassword other = (UserPassword) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        }
        else if (!login.equals(other.login))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        }
        else if (!password.equals(other.password))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.login == null) ? 0 : this.login.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserPassword (");

        sb.append(id);
        sb.append(", ").append(login);
        sb.append(", ").append(password);

        sb.append(")");
        return sb.toString();
    }
}
