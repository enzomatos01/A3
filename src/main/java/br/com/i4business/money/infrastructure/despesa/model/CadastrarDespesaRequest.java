package br.com.i4business.money.infrastructure.despesa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public record CadastrarDespesaRequest(
        @JsonProperty("nome") String nome,
        @JsonProperty("is_ativo") Boolean ativo,
        @JsonProperty("itens") List<ItemDespesaRequest> itens
) {
    public boolean isAtivo() {
        return this.ativo != null ? this.ativo : true;
    }

    public List<ItemDespesaRequest> itens() {
        return this.itens != null ? this.itens : Collections.emptyList();
    }
}
