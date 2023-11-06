package br.com.i4business.money.infrastructure.pote.presenter;

import br.com.i4business.money.application.pote.recuperar.get.PoteResponse;
import br.com.i4business.money.application.pote.recuperar.list.PoteListResponse;
import br.com.i4business.money.infrastructure.pote.model.ListarPotesResponse;

public interface PoteApiPresenter {

    static br.com.i4business.money.infrastructure.pote.model.PoteResponse present(final PoteResponse output) {
        return new br.com.i4business.money.infrastructure.pote.model.PoteResponse(
                output.id().getValue(),
                output.nome(),
                output.descricao(),
                output.isAtivo(),
                output.categorias(),
                output.criadoEm(),
                output.atualizadoEm(),
                output.deletadoEm()
        );
    }

    static ListarPotesResponse present(final PoteListResponse output) {
        return new ListarPotesResponse(
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
