-- V2__create_categories_and_link_products.sql

-- 1. Create the new 'categories' table
CREATE TABLE categories (
    category_id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

-- 2. Add a new column to the 'products' table for the foreign key
ALTER TABLE products
ADD COLUMN category_id UUID;

-- 3. Add the foreign key constraint to link products to categories
ALTER TABLE products
ADD CONSTRAINT fk_products_categories
FOREIGN KEY (category_id)
REFERENCES categories(category_id);

-- 4. Drop the old, simple 'category' text column
ALTER TABLE products
DROP COLUMN category;