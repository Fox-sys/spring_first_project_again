--liquibase formatted sql

--changeset fox-sys:create-users-table
CREATE
EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users_table
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    first_name VARCHAR                                    NOT NULL,
    last_name  VARCHAR                                    NOT NULL,
    username   VARCHAR(255)                               NOT NULL UNIQUE
);
--rollback DROP TABLE users_table;