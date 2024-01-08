package com.anast.lms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterRequest {

    @JsonProperty("auth_info")
    private UserAuthInfo authInfo;

    @JsonProperty("auth_details")
    private UserDetail userDetail;

    public UserRegisterRequest(UserAuthInfo authInfo, UserDetail userDetail) {
        this.authInfo = authInfo;
        this.userDetail = userDetail;
    }

    public UserRegisterRequest() {
    }

    public UserAuthInfo getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(UserAuthInfo authInfo) {
        this.authInfo = authInfo;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }
}
