alter table lms.lms_user.user_role ADD constraint role_code_fk foreign key (role_code)
references lms.lms_user.role(code);