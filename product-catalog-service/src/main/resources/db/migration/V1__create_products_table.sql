-- V1__create_products_table.sql

CREATE TABLE products (
    -- Using UUID for the primary key
    product_id UUID PRIMARY KEY,

    -- Core product details
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(100) NOT NULL,

    -- Link to an image stored elsewhere (e.g., S3)
    image_url TEXT,

    -- Timestamp for when the product was created
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

-- Add indexes for faster searching and filtering
CREATE INDEX idx_products_name ON products(name);
CREATE INDEX idx_products_category ON products(category);