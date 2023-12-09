CREATE TABLE IF NOT EXISTS lms_user.user_role (
    login varchar not null,
    role_code varchar not null,
    CONSTRAINT login_fk FOREIGN KEY (login) REFERENCES lms_user.user_info(login)
);

insert into lms_user.role values ('STUDENT', 'Обучающийся');
insert into lms_user.role values ('TEACHER', 'Преподаватель');
insert into lms_user.user_role values ('debug', 'STUDENT');
insert into lms_user.user_role values ('debug', 'TEACHER');