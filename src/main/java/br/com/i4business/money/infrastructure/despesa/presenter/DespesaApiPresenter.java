package br.com.i4business.money.infrastructure.despesa.presenter;

import br.com.i4business.money.application.categoria.recuperar.get.GetCategoriaByIdResponse;
import br.com.i4business.money.application.categoria.recuperar.list.CategoriaListResponse;
import br.com.i4business.money.application.despesa.recuperar.get.GetDespesaByIdResponse;
import br.com.i4business.money.application.despesa.recuperar.get.GetDespesaItemResponse;
import br.com.i4business.money.application.despesa.recuperar.list.DespesaListResponse;
import br.com.i4business.money.infrastructure.categoria.model.CategoriaResponse;
import br.com.i4business.money.infrastructure.categoria.model.ListarCategoriasResponse;
import br.com.i4business.money.infrastructure.despesa.model.DespesaResponse;
import br.com.i4business.money.infrastructure.despesa.model.ItemDespesaResponse;
import br.com.i4business.money.infrastructure.despesa.model.ListarDespesasResponse;

public interface DespesaApiPresenter {

    static DespesaResponse present(final GetDespesaByIdResponse output) {
        return new DespesaResponse(
                output.id().getValue(),
                output.nome(),
                output.itens().stream()
                        .map(ItemDespesaResponse::fromGetDespesaItemResponse)
                        .toList(),
                output.isAtivo(),
                output.criadoEm(),
                output.atualizadoEm(),
                output.deletadoEm()
        );
    }

    static ListarDespesasResponse present(final DespesaListResponse output) {
        return new ListarDespesasResponse(
                output.id().getValue(),
                output.nome(),
                output.isAtivo(),
                output.criadoEm(),
                output.atualizadoEm(),
                output.deletadoEm()
        );
    }
}
