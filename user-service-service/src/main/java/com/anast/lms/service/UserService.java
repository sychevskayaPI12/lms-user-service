package com.anast.lms.service;

import com.anast.lms.generated.jooq.Tables;
import com.anast.lms.generated.jooq.tables.records.UserPasswordRecord;
import com.anast.lms.model.UserAuthInfo;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final DSLContext context;


    public UserService(DSLContext context) {
        this.context = context;
    }

    public UserAuthInfo getUserAuthInfoByLogin(String login) {
        return mapUserAuthInfoRecord(context.selectFrom(Tables.USER_PASSWORD).where(Tables.USER_PASSWORD.LOGIN.eq(login))
                .fetchAny());
    }

    private UserAuthInfo mapUserAuthInfoRecord(UserPasswordRecord rec) {
        return new UserAuthInfo(
                rec.getLogin(),
                rec.getPassword()
        );
    }
}
