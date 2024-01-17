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

    public boolean checkIsConfirmedAccountPresent(String login) {
        return userRepository.isConfirmedAccountPresent(login);
    }

    public UserAuthInfo getUserAuthInfoByLogin(String login) {
        if(!checkIsConfirmedAccountPresent(login)) {
            return null;
        }
        return userRepository.getUserAuthInfo(login);
    }

    public UserDetail getUserDetail(String login) {
        return userRepository.getUserDetail(login);
    }

    @Transactional
    public void registerNewUser(UserRegisterRequest registerRequest) throws Exception {

        if(checkIsConfirmedAccountPresent(registerRequest.getAuthInfo().getLogin())) {
            throw new Exception(String.format("Пользователь с логином %s уже существует", registerRequest.getAuthInfo().getLogin()));
        }

        userRepository.createUser(registerRequest.getUserDetail());
        userRepository.insertUserAuthInfo(registerRequest.getAuthInfo());

    }

    @Transactional
    public void deleteUser(String login) {
        userRepository.deleteUserRoles(login);
        userRepository.deleteUserPassword(login);
        userRepository.deleteUser(login);
    }

    public void confirmAccount(String login) {
        userRepository.confirmAccount(login);
    }
}
