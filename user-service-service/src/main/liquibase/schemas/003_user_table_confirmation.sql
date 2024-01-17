alter table lms.lms_user.user_info drop column confirmed;

create table lms.lms_user.user_account(
    login varchar not null unique,
    confirmed boolean not null default false,
    constraint login_acc_pk primary key (login)
);

insert into lms.lms_user.user_account values ('teacher'), ('debug');

alter table lms.lms_user.user_password drop constraint login_fk;
alter table lms.lms_user.user_password add foreign key(login) REFERENCES lms.lms_user.user_account(login);

alter table lms.lms_user.user_info add foreign key(login) references lms.lms_user.user_account(login);

alter table lms.lms_user.user_role add foreign key(login) references lms.lms_user.user_account(login);
