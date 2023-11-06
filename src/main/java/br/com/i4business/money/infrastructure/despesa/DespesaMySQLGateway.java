package br.com.i4business.money.infrastructure.despesa;

import br.com.i4business.money.domain.data.despesa.Despesa;
import br.com.i4business.money.domain.data.despesa.DespesaGateway;
import br.com.i4business.money.domain.data.despesa.DespesaID;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;
import br.com.i4business.money.infrastructure.despesa.persistence.DespesaJpaEntity;
import br.com.i4business.money.infrastructure.despesa.persistence.DespesaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static br.com.i4business.money.infrastructure.utils.SpecificationUtils.like;

@Component
public class DespesaMySQLGateway implements DespesaGateway {

    private final DespesaRepository repository;

    public DespesaMySQLGateway(DespesaRepository repository) {
        this.repository = repository;
    }
    @Override
    public Despesa cadastrar(final Despesa despesa) {
     return save(despesa);
    }
    public Despesa atualizar(final Despesa despesa) {
        return save(despesa);
    }

    @Override
    public Optional<Despesa> findById(final DespesaID anId) {
        return this.repository.findById(anId.getValue())
                .map(DespesaJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Despesa> findAll(final SearchQuery aQuery) {
        // Paginação
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        // Busca dinamica pelo criterio terms (name ou description)
        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult =  this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(DespesaJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public void deleteById(final DespesaID anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public List<DespesaID> existsByIds(Iterable<DespesaID> poteIDs) {
        final var ids = StreamSupport.stream(poteIDs.spliterator(), false)
                .map(DespesaID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(DespesaID::de)
                .toList();
    }

    private Despesa save(final Despesa despesa) {
        return this.repository.save(DespesaJpaEntity.toEntity(despesa)).toAggregate();
    }

    private Specification<DespesaJpaEntity> assembleSpecification(final String str) {
        final Specification<DespesaJpaEntity> nomeLike = like("nome", str);
        return nomeLike;
    }

}
