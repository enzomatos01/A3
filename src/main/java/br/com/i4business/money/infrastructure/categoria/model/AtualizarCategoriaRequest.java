package br.com.i4business.money.infrastructure.categoria.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public record AtualizarCategoriaRequest(
        @JsonProperty("nome") String nome,
        @JsonProperty("descricao") String descricao,
        @JsonProperty("is_ativo") Boolean ativo,
        @JsonProperty("itens_id") List<String> itens
) {

    public boolean isAtivo() {
        return this.ativo != null ? this.ativo : true;
    }

    public List<String> itens() {
        return this.itens != null ? this.itens : Collections.emptyList();
    }
}
