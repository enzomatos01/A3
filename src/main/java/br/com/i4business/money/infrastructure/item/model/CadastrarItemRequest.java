package br.com.i4business.money.infrastructure.item.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CadastrarItemRequest(
        @JsonProperty("nome") String nome,
        @JsonProperty("descricao") String descricao,
        @JsonProperty("is_ativo") Boolean ativo
) {
}
