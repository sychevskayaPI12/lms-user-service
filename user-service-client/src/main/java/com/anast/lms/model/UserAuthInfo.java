package com.anast.lms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserAuthInfo {

    @JsonProperty("login")
    private String  login;

    @JsonProperty("password")
    private String  password;

    @JsonProperty("roles")
    private List<String> roles;

    public UserAuthInfo() {}

    public UserAuthInfo(String login, String password, List<String> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
