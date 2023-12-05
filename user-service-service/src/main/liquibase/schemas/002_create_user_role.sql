CREATE TABLE IF NOT EXISTS lms_user.user_role (
    login varchar not null,
    role_code varchar not null,
    CONSTRAINT login_fk FOREIGN KEY (login) REFERENCES lms_user.user_info(login)
);

