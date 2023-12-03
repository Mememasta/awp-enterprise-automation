create table if not exists awp_enterprise_automation.products_with_balance
(
    id              serial,
    area_id         int not null,
    product_id      bigint not null,
    coefficient     int not null default 0
);

ALTER TABLE awp_enterprise_automation.products_with_balance
    ADD CONSTRAINT products_with_balance_id_pkey PRIMARY KEY (id);

CREATE UNIQUE INDEX CONCURRENTLY products_with_balance_uindex ON awp_enterprise_automation.products_with_balance (area_id, product_id);