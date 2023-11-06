CREATE TABLE categoria
(
    id            CHAR(32)     NOT NULL PRIMARY KEY,
    nome          VARCHAR(255) NOT NULL,
    descricao     VARCHAR(4000),
    ativo         BOOLEAN      NOT NULL DEFAULT TRUE,
    criado_em     DATETIME(6) NOT NULL,
    atualizado_em DATETIME(6) NOT NULL,
    deletado_em   DATETIME(6) NULL
);

