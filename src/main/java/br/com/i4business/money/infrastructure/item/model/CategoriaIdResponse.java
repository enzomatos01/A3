package br.com.i4business.money.infrastructure.item.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoriaIdResponse(
        @JsonProperty("id") String id
) {

}
