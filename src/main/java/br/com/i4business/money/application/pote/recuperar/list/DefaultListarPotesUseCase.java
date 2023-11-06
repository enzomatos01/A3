package br.com.i4business.money.application.pote.recuperar.list;

import br.com.i4business.money.domain.data.pote.PoteGateway;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListarPotesUseCase extends ListarPotesUseCase {

    private final PoteGateway poteGateway;

    public DefaultListarPotesUseCase(PoteGateway poteGateway) {
        this.poteGateway = Objects.requireNonNull(poteGateway);
    }

    @Override
    public Pagination<PoteListResponse> execute(final SearchQuery searchQuery) {
        return this.poteGateway.findAll(searchQuery)
                .map(PoteListResponse::de);
    }
}
