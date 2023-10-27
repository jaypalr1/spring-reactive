-- drop table User_Details_Entity;

CREATE TABLE IF NOT EXISTS Users
(
    id                      uuid not null primary key,
    name                    VARCHAR,
    username                VARCHAR,
    password                VARCHAR,
    authorities             VARCHAR,
    account_non_expired     boolean,
    account_non_locked      boolean,
    credentials_non_expired boolean,
    enabled                 boolean
);

-- alter table user_details_entity owner to postgres;
