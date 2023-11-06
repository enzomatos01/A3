CREATE TABLE pote_categoria
(
    pote_id    CHAR(32) NOT NULL,
    categoria_id CHAR(32) NOT NULL,
    CONSTRAINT idx_pote_categoria UNIQUE (pote_id, categoria_id),
    CONSTRAINT fk_pote_categoria_id FOREIGN KEY (pote_id) REFERENCES pote (id) ON DELETE CASCADE,
    CONSTRAINT fk_categoria_pote_id FOREIGN KEY (categoria_id) REFERENCES categoria (id) ON DELETE CASCADE
);