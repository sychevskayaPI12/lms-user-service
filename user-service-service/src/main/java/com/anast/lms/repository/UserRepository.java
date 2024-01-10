package com.anast.lms.repository;

import com.anast.lms.generated.jooq.Tables;
import com.anast.lms.generated.jooq.tables.records.UserInfoRecord;
import com.anast.lms.generated.jooq.tables.records.UserPasswordRecord;
import com.anast.lms.model.UserAuthInfo;
import com.anast.lms.model.UserDetail;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.anast.lms.generated.jooq.Tables.*;

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
        return context.selectFrom(USER_INFO)
                .where(USER_INFO.LOGIN.eq(login))
                .fetchAny(this::mapUserDetail);
    }

    public void createUser(UserDetail userDetail) {
        context.insertInto(USER_INFO)
            .set(USER_INFO.LOGIN, userDetail.getLogin())
            .set(USER_INFO.FULL_NAME, userDetail.getFullName())
            .set(USER_INFO.MAIL, userDetail.getMail())
            .execute();
    }

    public void insertUserAuthInfo(UserAuthInfo info) {
        context.insertInto(USER_PASSWORD)
                .set(USER_PASSWORD.LOGIN, info.getLogin())
                .set(USER_PASSWORD.PASSWORD, info.getPassword())
                .execute();
        insertUserRoles(info.getRoles(), info.getLogin());
    }

    public void insertUserRoles(List<String> roles, String login) {
        for(String role : roles) {
            context.insertInto(USER_ROLE)
                    .set(USER_ROLE.LOGIN, login)
                    .set(USER_ROLE.ROLE_CODE, role)
                    .execute();
        }
    }

    public void deleteUserRoles(String login) {
        context.deleteFrom(USER_ROLE)
        .where(USER_ROLE.LOGIN.eq(login))
        .execute();
    }

    public void deleteUserPassword(String login) {
        context.deleteFrom(USER_PASSWORD)
                .where(USER_PASSWORD.LOGIN.eq(login))
                .execute();
    }

    public void deleteUser(String login) {
        context.deleteFrom(USER_INFO)
                .where(USER_INFO.LOGIN.eq(login))
                .execute();
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
