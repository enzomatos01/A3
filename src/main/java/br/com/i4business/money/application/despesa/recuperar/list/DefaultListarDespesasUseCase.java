package br.com.i4business.money.application.despesa.recuperar.list;

import br.com.i4business.money.domain.data.despesa.DespesaGateway;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListarDespesasUseCase extends ListarDespesasUseCase {

    private final DespesaGateway despesaGateway;

    public DefaultListarDespesasUseCase(DespesaGateway despesaGateway) {
        this.despesaGateway = Objects.requireNonNull(despesaGateway);
    }

    @Override
    public Pagination<DespesaListResponse> execute(final SearchQuery searchQuery) {
        return this.despesaGateway.findAll(searchQuery)
                .map(DespesaListResponse::de);
    }
}
