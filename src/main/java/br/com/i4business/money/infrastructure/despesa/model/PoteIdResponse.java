package br.com.i4business.money.infrastructure.despesa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PoteIdResponse(
        @JsonProperty("id") String id
) {

}
