-- V1__create_users_table.sql
-- NOTE: We are NOT using a native PostgreSQL ENUM type anymore.

CREATE TABLE users (
    user_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,

    -- Using VARCHAR for the role for better JPA compatibility
    role VARCHAR(255) NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    -- Add a CHECK constraint to mimic ENUM behavior and ensure data integrity
    CONSTRAINT role_check CHECK (role IN ('CUSTOMER', 'VENDOR', 'ADMIN'))
);

CREATE INDEX idx_users_email ON users(email);