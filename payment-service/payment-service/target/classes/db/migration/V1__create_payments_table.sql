-- V1__create_payments_table.sql

CREATE TABLE payments (
    payment_id UUID PRIMARY KEY,
    order_id UUID NOT NULL UNIQUE, -- An order should only have one payment record
    status VARCHAR(50) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    method VARCHAR(100),
    transaction_ref VARCHAR(255), -- ID from the external payment provider (e.g., Stripe)
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    -- Ensure status is one of the allowed values
    CONSTRAINT status_check CHECK (status IN ('PENDING', 'SUCCESS', 'FAILED', 'REFUNDED'))
);

CREATE INDEX idx_payments_order_id ON payments(order_id);