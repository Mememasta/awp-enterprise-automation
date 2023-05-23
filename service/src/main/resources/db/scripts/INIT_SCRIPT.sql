create table if not exists awp_enterprise_automation.users
(
    id              uuid not null,
    first_name      varchar(1024) not null,
    last_name       varchar(1024) not null,
    phone_number    varchar(1024) not null,
    password        varchar(1024) not null,
    authorities     varchar(128)[] not null,
    hash            char(128)
);

ALTER TABLE awp_enterprise_automation.users
    ADD CONSTRAINT users_id_pkey PRIMARY KEY (id);

DROP INDEX IF EXISTS users_unique_index;
CREATE UNIQUE INDEX CONCURRENTLY user_uindex ON awp_enterprise_automation.users (id, phone_number, hash);