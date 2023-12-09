package com.anast.lms.repository;

import com.anast.lms.generated.jooq.Tables;
import com.anast.lms.generated.jooq.tables.records.UserInfoRecord;
import com.anast.lms.generated.jooq.tables.records.UserPasswordRecord;
import com.anast.lms.model.UserAuthInfo;
import com.anast.lms.model.UserDetail;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final DSLContext context;

    public UserRepository(DSLContext context) {
        this.context = context;
    }

    public UserAuthInfo getUserAuthInfo(String login) {
        UserPasswordRecord userPasswordRecord = context
                .selectFrom(Tables.USER_PASSWORD)
                .where(Tables.USER_PASSWORD.LOGIN.eq(login))
                .fetchAny();
        if(userPasswordRecord == null) {
            return null;
        }
        Result roles = context.select(Tables.USER_ROLE.ROLE_CODE)
                .from(Tables.USER_ROLE)
                .where(Tables.USER_ROLE.LOGIN.eq(login))
                .fetch();
        return mapUserAuthInfoRecord(userPasswordRecord, roles);
    }

    public UserDetail getUserDetail(String login) {
        return context.selectFrom(Tables.USER_INFO)
                .where(Tables.USER_INFO.LOGIN.eq(login))
                .fetchAny(this::mapUserDetail);
    }

    private UserAuthInfo mapUserAuthInfoRecord(UserPasswordRecord userPasswordRecord, Result roles) {
        return new UserAuthInfo(
                userPasswordRecord.getLogin(),
                userPasswordRecord.getPassword(),
                roles.getValues(Tables.USER_ROLE.ROLE_CODE));
    }

    private UserDetail mapUserDetail(UserInfoRecord record) {
        return new UserDetail(
                record.getLogin(),
                record.getFullName(),
                record.getMail());
    }
}
