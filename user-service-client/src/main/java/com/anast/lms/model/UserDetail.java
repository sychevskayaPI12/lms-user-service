package com.anast.lms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetail {

    @JsonProperty("login")
    private String login;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("mail")
    private String mail;

    public UserDetail(String login, String fullName, String mail) {
        this.login = login;
        this.fullName = fullName;
        this.mail = mail;
    }

    public UserDetail(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
