--liquibase formatted sql

--changeset fox-sys:add-sessions-table splitStatements:true endDelimiter:;
CREATE TABLE sessions_table
(
    id            UUID PRIMARY KEY         DEFAULT gen_random_uuid() NOT NULL,
    user_id       UUID                                               NOT NULL REFERENCES users_table (id) ON DELETE CASCADE,
    user_agent    TEXT,
    ip_address    TEXT,
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    last_activity TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    is_active     BOOLEAN                  DEFAULT TRUE
);

--rollback DROP TABLE sessions_table;