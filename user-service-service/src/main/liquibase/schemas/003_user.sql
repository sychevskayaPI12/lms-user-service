insert into lms.lms_user.role values ('MODERATOR', 'Модератор');

alter table lms.lms_user.user_info alter column login set not null;

alter table lms.lms_user.user_info add column confirmed boolean not null default false;