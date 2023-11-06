package br.com.i4business.money.infrastructure.item.presenter;

import br.com.i4business.money.application.item.recuperar.get.GetItemByIdResponse;
import br.com.i4business.money.application.item.recuperar.list.ItemListOutput;
import br.com.i4business.money.infrastructure.item.model.CategoriaIdResponse;
import br.com.i4business.money.infrastructure.item.model.ItemResponse;
import br.com.i4business.money.infrastructure.item.model.ListarItensResponse;

public interface ItemApiPresenter {

    static ItemResponse present(final GetItemByIdResponse output) {
        return new ItemResponse(
                output.id().getValue(),
                output.nome(),
                output.descricao(),
                output.isAtivo(),
                output.criadoEm(),
                output.atualizadoEm(),
                output.deletadoEm()
        );
    }

    static ListarItensResponse present(final ItemListOutput output) {
        return new ListarItensResponse(
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
