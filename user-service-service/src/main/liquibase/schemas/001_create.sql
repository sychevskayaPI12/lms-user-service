CREATE SCHEMA IF NOT EXISTS lms_user;


CREATE TABLE lms_user.user_info (
    login varchar not null unique,
    full_name varchar not null,
    mail varchar unique,
    CONSTRAINT login_pk PRIMARY KEY (login)
);

CREATE SEQUENCE lms_user.user_password_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483
    START WITH 1;

CREATE TABLE lms_user.user_password (
  id integer not null default nextval('lms_user.user_password_id_seq'::regclass),
  login varchar not null unique,
  password varchar not null,
  CONSTRAINT user_password_id_pk PRIMARY KEY(id),
  CONSTRAINT login_fk FOREIGN KEY(login) REFERENCES lms_user.user_info(login)
);


CREATE TABLE IF NOT EXISTS lms_user.role (
    code varchar not null unique,
    description varchar,
    constraint role_code_pk PRIMARY KEY (code)
);

INSERT INTO lms_user.user_info(login, full_name, mail)
VALUES('debug', 'Дебаг Дебагович', 'debug@mail.ru');

INSERT INTO lms_user.user_password(login, password) VALUES ('debug', 'debug');


