package com.anast.lms.service;

import com.anast.lms.model.UserAuthInfo;
import com.anast.lms.model.UserDetail;
import com.anast.lms.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserAuthInfo getUserAuthInfoByLogin(String login) {
        return userRepository.getUserAuthInfo(login);
    }

    public UserDetail getUserDetail(String login) {
        return userRepository.getUserDetail(login);
    }

}
