package br.com.i4business.money.application.categoria.cadastrar;

import br.com.i4business.money.domain.data.categoria.Categoria;

public record CadastrarCategoriaResponse(
        String id
) {
    public static CadastrarCategoriaResponse de(final String anId) {
        return new CadastrarCategoriaResponse(anId);
    }

    public static CadastrarCategoriaResponse de(final Categoria aCategory) {
        return new CadastrarCategoriaResponse(aCategory.getId().getValue());
    }
}
