package br.com.i4business.money.infrastructure.despesa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ItemDespesaRequest(
        @JsonProperty("item_id") String id,
        @JsonProperty("valor") BigDecimal valor
        ) {
}
