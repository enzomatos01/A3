CREATE TABLE despesa_item
(
    despesa_id    CHAR(32) NOT NULL,
    item_id CHAR(32) NOT NULL,
    valor NUMERIC(10,2),
    CONSTRAINT idx_despesa_item UNIQUE (despesa_id, item_id),
    CONSTRAINT fk_despesa_item_id FOREIGN KEY (despesa_id) REFERENCES despesa (id) ON DELETE CASCADE,
    CONSTRAINT fk_item_despesa_id FOREIGN KEY (item_id) REFERENCES item (id) ON DELETE CASCADE
);