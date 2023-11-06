package br.com.i4business.money.infrastructure.despesa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record DespesaResponse(
        @JsonProperty("id") String id,
        @JsonProperty("nome") String nome,
        @JsonProperty("itens_id") List<ItemDespesaResponse> itens,
        @JsonProperty("is_ativo") Boolean ativo,
        @JsonProperty("criado_em") Instant criadoEm,
        @JsonProperty("alterado_em") Instant alteradoEm,
        @JsonProperty("deletado_em") Instant deletadoEm
        ) {
}
