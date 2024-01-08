package com.anast.lms.client;

import com.anast.lms.model.UserAuthInfo;
import com.anast.lms.model.UserDetail;
import com.anast.lms.model.UserRegisterRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public interface UserRestService {

    @GetMapping("/user/{login}/auth_info")
    UserAuthInfo getUserAuthInfoByLogin(@PathVariable("login") String login);

    @GetMapping("/user/{login}/detail")
    UserDetail getUserDetail(@PathVariable("login") String login);

    @PostMapping("/user/register")
    void registerNewUser(@RequestBody UserRegisterRequest registerRequest) throws Exception;
}
