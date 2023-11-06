package br.com.i4business.money.application.categoria.atualizar;

import br.com.i4business.money.domain.data.categoria.Categoria;

public record AtualizarCategoriaResponse(
        String id
) {
    public static AtualizarCategoriaResponse de(final String anId) {
        return new AtualizarCategoriaResponse(anId);
    }

    public static AtualizarCategoriaResponse de(final Categoria aCategory) {
        return new AtualizarCategoriaResponse(aCategory.getId().getValue());
    }
}
