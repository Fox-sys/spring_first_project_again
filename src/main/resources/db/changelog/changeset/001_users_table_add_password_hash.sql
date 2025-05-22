--liquibase formatted sql

--changeset fox-sys:add-password-hash-to-user-table splitStatements:true endDelimiter:;
ALTER TABLE users_table ADD COLUMN IF NOT EXISTS password_hash VARCHAR(255);
UPDATE users_table SET password_hash = 'none' WHERE password_hash IS NULL;
ALTER TABLE users_table ALTER COLUMN password_hash SET NOT NULL;

--rollback ALTER TABLE users_table DROP COLUMN IF EXISTS password_hash;