package br.com.i4business.money.infrastructure.pote.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public record AtualizarPoteRequest(
        @JsonProperty("nome") String nome,
        @JsonProperty("descricao") String descricao,
        @JsonProperty("is_ativo") Boolean ativo,
        @JsonProperty("categorias_id") List<String> categorias
) {

    public boolean isAtivo() {
        return this.ativo != null ? this.ativo : true;
    }

    public List<String> categorias() {
        return this.categorias != null ? this.categorias : Collections.emptyList();
    }

}
