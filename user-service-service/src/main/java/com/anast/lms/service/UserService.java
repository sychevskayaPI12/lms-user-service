package com.anast.lms.service;

import com.anast.lms.model.UserAuthInfo;
import com.anast.lms.model.UserDetail;
import com.anast.lms.model.UserRegisterRequest;
import com.anast.lms.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void registerNewUser(UserRegisterRequest registerRequest) throws Exception {

        UserAuthInfo userAuthInfo = userRepository.getUserAuthInfo(registerRequest.getAuthInfo().getLogin());
        if(userAuthInfo != null) {
            throw new Exception(String.format("Пользователь с логином %s уже существует", userAuthInfo.getLogin()));
        }

        userRepository.createUser(registerRequest.getUserDetail());
        userRepository.insertUserAuthInfo(registerRequest.getAuthInfo());

    }
}
