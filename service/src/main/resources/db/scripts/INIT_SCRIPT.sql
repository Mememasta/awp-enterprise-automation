create table if not exists awp_enterprise_automation.users
(
    id              uuid not null,
    first_name      varchar(1024) not null,
    last_name       varchar(1024) not null,
    phone_number    varchar(1024) not null,
    password        varchar(1024) not null,
    authorities     varchar(128)[] not null
);

ALTER TABLE awp_enterprise_automation.users
    ADD CONSTRAINT users_id_pkey PRIMARY KEY (id);

DROP INDEX IF EXISTS user_phone_unique_index;
CREATE UNIQUE INDEX CONCURRENTLY user_uindex ON awp_enterprise_automation.users (phone_number);

INSERT INTO awp_enterprise_automation.users (id, first_name, last_name, phone_number, password, authorities) VALUES ('ecbee40b-79b5-4648-988d-bbce78354103','Никита','Русских','79641808781','$2a$12$aazdjbr1gBoEeixpYVJ3AeT9kAtVleGim9mXAT.S4PRszxw7wuwx.','{ROLE_USER,ROLE_ADMIN}');