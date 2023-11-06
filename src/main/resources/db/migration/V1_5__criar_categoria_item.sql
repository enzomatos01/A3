CREATE TABLE categoria_item
(
    categoria_id    CHAR(32) NOT NULL,
    item_id CHAR(32) NOT NULL,
    CONSTRAINT idx_categoria_item UNIQUE (categoria_id, item_id),
    CONSTRAINT fk_categoria_item_id FOREIGN KEY (categoria_id) REFERENCES categoria (id) ON DELETE CASCADE,
    CONSTRAINT fk_item_categoria_id FOREIGN KEY (item_id) REFERENCES item (id) ON DELETE CASCADE
);