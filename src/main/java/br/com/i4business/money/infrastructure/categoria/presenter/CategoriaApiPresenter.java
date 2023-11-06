package br.com.i4business.money.infrastructure.categoria.presenter;

import br.com.i4business.money.application.categoria.recuperar.get.GetCategoriaByIdResponse;
import br.com.i4business.money.application.categoria.recuperar.list.CategoriaListResponse;
import br.com.i4business.money.infrastructure.categoria.model.CategoriaResponse;
import br.com.i4business.money.infrastructure.categoria.model.ListarCategoriasResponse;

public interface CategoriaApiPresenter {

    static CategoriaResponse present(final GetCategoriaByIdResponse output) {
        return new CategoriaResponse(
                output.id().getValue(),
                output.nome(),
                output.descricao(),
                output.isAtivo(),
                output.criadoEm(),
                output.atualizadoEm(),
                output.deletadoEm(),
                output.itens()
        );
    }

    static ListarCategoriasResponse present(final CategoriaListResponse output) {
        return new ListarCategoriasResponse(
                output.id().getValue(),
                output.nome(),
                output.descricao(),
                output.isAtivo(),
                output.criadoEm(),
                output.atualizadoEm(),
                output.deletadoEm()
        );
    }
}
