ALTER TABLE awp_enterprise_automation.users
    ADD CONSTRAINT fk_users_area_id  FOREIGN KEY (area_id) REFERENCES areas (id);