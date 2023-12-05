-- Перед выполнением скриптов БД с помощью Liquibase следует создать схему для хранения таблиц утилиты как показано ниже.

CREATE SCHEMA IF NOT EXISTS changelog;

-- Если схема ft_changelog существует и скрипты запущены для создания БД с нуля, следует очистить служебные таблицы Liquibase.

DELETE FROM changelog.databasechangelog;
DELETE FROM changelog.databasechangeloglock;

