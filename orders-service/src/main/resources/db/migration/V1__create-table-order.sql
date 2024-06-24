create table orders(
    id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    status VARCHAR(150) NOT NULL,
    PRIMARY KEY(id)
);

create table order_items (
    order_id UUID NOT NULL,
    id BIGINT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY(order_id) REFERENCES orders(id)
);