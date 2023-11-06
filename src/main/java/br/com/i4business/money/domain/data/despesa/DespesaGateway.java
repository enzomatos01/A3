package br.com.i4business.money.domain.data.despesa;

import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface DespesaGateway {
    Despesa cadastrar(Despesa despesa);

    Despesa atualizar(Despesa despesa);

    public void deleteById(DespesaID id);

    Optional<Despesa> findById(DespesaID id);

    Pagination<Despesa> findAll(SearchQuery aQuery);

    List<DespesaID> existsByIds(Iterable<DespesaID> ids);
}
