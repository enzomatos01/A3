package br.com.i4business.money.infrastructure.item.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AtualizarItemRequest(
        @JsonProperty("nome") String nome,
        @JsonProperty("descricao") String descricao,
        @JsonProperty("is_ativo") Boolean ativo,
        @JsonProperty("categoria_id") String categoria
) {
}
