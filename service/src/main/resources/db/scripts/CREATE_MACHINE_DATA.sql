create sequence if not exists awp_enterprise_automation.machine_data_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 100000000
    CYCLE;

create table if not exists awp_enterprise_automation.machine_data
(
    id              bigint primary key,
    topic           varchar(128) not null,
    value           varchar(128) not null,
    event_date      timestamp
);

CREATE INDEX CONCURRENTLY machine_data_uindex ON awp_enterprise_automation.machine_data (topic);