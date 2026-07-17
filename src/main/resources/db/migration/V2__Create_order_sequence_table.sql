CREATE TABLE order_sequences (
    sequence_name VARCHAR(50) PRIMARY KEY,
    next_value BIGINT NOT NULL
);

INSERT INTO order_sequences (sequence_name, next_value)
VALUES ('order_number', 1);