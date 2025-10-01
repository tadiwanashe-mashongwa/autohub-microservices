-- V1__create_inventory_table.sql

CREATE TABLE inventory (
    -- Primary key for the inventory record itself
    inventory_id UUID PRIMARY KEY,

    -- A logical foreign key to the Product in the Product Catalog Service
    -- This must be unique to ensure one inventory record per product.
    product_id UUID NOT NULL UNIQUE,

    -- The total number of items in stock
    quantity INT NOT NULL DEFAULT 0,

    -- The number of items reserved for open orders
    reserved INT NOT NULL DEFAULT 0,

    -- Timestamp for when the stock level was last updated
    last_updated TIMESTAMP WITH TIME ZONE NOT NULL
);

-- Add an index on product_id for fast lookups
CREATE INDEX idx_inventory_product_id ON inventory(product_id);