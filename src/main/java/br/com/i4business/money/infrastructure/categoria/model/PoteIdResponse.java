package br.com.i4business.money.infrastructure.categoria.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PoteIdResponse(
        @JsonProperty("id") String id
) {

}
