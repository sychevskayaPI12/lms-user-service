package com.anast.lms.controller;

import com.anast.lms.client.UserRestService;
import com.anast.lms.model.UserAuthInfo;
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
}
