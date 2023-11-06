package br.com.i4business.money.infrastructure.pote.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record PoteResponse(
        @JsonProperty("id") String id,
        @JsonProperty("nome") String name,
        @JsonProperty("descricao") String descricao,
        @JsonProperty("is_ativo") Boolean ativo,
        @JsonProperty("categorias_id") List<String> categorias,
        @JsonProperty("criado_em") Instant criadoEm,
        @JsonProperty("alterado_em") Instant alteradoEm,
        @JsonProperty("deletado_em") Instant deletadoEm
) {
}
