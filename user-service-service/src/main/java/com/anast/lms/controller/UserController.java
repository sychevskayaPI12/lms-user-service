package com.anast.lms.controller;

import com.anast.lms.client.UserRestService;
import com.anast.lms.model.UserAuthInfo;
import com.anast.lms.model.UserDetail;
import com.anast.lms.model.UserRegisterRequest;
import com.anast.lms.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserRestService {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserAuthInfo getUserAuthInfoByLogin(String login) {
        return userService.getUserAuthInfoByLogin(login);
    }

    @Override
    public UserDetail getUserDetail(String login) {
        return userService.getUserDetail(login);
    }

    @Override
    public void registerNewUser(UserRegisterRequest registerRequest) throws Exception {
        userService.registerNewUser(registerRequest);
    }

    @Override
    public void deleteUser(String login) {
        userService.deleteUser(login);
    }

    @Override
    public void confirmAccount(String login) {
        userService.confirmAccount(login);
    }
}
