package br.com.i4business.money.domain.data.pote;


import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface PoteGateway {
    Pote cadastrar(Pote pote);

    Pote atualizar(Pote pote);

    public void deleteById(PoteID id);

    Optional<Pote> findById(PoteID id);

    Pagination<Pote> findAll(SearchQuery aQuery);

    List<PoteID> existsByIds(Iterable<PoteID> ids);
}
