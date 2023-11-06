package br.com.i4business.money.infrastructure.pote;

import br.com.i4business.money.domain.data.pote.Pote;
import br.com.i4business.money.domain.data.pote.PoteGateway;
import br.com.i4business.money.domain.data.pote.PoteID;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;
import br.com.i4business.money.infrastructure.pote.persistence.PoteJpaEntity;
import br.com.i4business.money.infrastructure.pote.persistence.PoteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static br.com.i4business.money.infrastructure.utils.SpecificationUtils.like;

@Component
public class PoteMySQLGateway implements PoteGateway {

    private final PoteRepository repository;

    public PoteMySQLGateway(PoteRepository repository) {
        this.repository = repository;
    }
    @Override
    public Pote cadastrar(final Pote pote) {
     return save(pote);
    }

    @Override
    public Pote atualizar(Pote pote) {
        return save(pote);
    }

    @Override
    public void deleteById(PoteID id) {
        final String anIdValue = id.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<Pote> findById(PoteID id) {
        return this.repository.findById(id.getValue())
                .map(PoteJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Pote> findAll(final SearchQuery aQuery) {
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
                pageResult.map(PoteJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public List<PoteID> existsByIds(Iterable<PoteID> poteIDs) {
        final var ids = StreamSupport.stream(poteIDs.spliterator(), false)
                .map(PoteID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(PoteID::de)
                .toList();
    }

    private Pote save(final Pote pote) {
        return this.repository.save(PoteJpaEntity.de(pote)).toAggregate();
    }

    private Specification<PoteJpaEntity> assembleSpecification(final String str) {
        final Specification<PoteJpaEntity> nomeLike = like("nome", str);
        final Specification<PoteJpaEntity> descricaoLike = like("descricao", str);
        return nomeLike.or(descricaoLike);
    }
}
