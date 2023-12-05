package com.anast.lms.client;

import com.anast.lms.model.UserAuthInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserRestService {

    @GetMapping("/user/{login}/auth_info")
    UserAuthInfo getUserAuthInfoByLogin(@PathVariable("login") String login);
}
