-- V1__create_orders_and_order_items_tables.sql

-- Create the main 'orders' table
CREATE TABLE orders (
    order_id UUID PRIMARY KEY,
    user_id UUID NOT NULL, -- Logical FK to the User in the Auth Service
    status VARCHAR(50) NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    -- Add a CHECK constraint for the status field to ensure data integrity
    CONSTRAINT status_check CHECK (status IN ('PENDING', 'PROCESSING', 'SHIPPED', 'CANCELLED'))
);

-- Create the 'order_items' table to hold the products within each order
CREATE TABLE order_items (
    order_item_id UUID PRIMARY KEY,
    order_id UUID NOT NULL, -- Physical FK to the 'orders' table
    product_id UUID NOT NULL, -- Logical FK to the Product in the Product Catalog Service
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL, -- The price of the product at the time of purchase

    -- Establish the foreign key relationship
    CONSTRAINT fk_order_items_orders
    FOREIGN KEY (order_id)
    REFERENCES orders(order_id)
);

-- Add an index on user_id for fast lookup of a user's orders
CREATE INDEX idx_orders_user_id ON orders(user_id);