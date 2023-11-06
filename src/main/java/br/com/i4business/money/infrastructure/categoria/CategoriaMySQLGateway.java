package br.com.i4business.money.infrastructure.categoria;

import br.com.i4business.money.domain.data.categoria.Categoria;
import br.com.i4business.money.domain.data.categoria.CategoriaGateway;
import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;
import br.com.i4business.money.infrastructure.categoria.persistence.CategoriaJpaEntity;
import br.com.i4business.money.infrastructure.categoria.persistence.CategoriaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static br.com.i4business.money.infrastructure.utils.SpecificationUtils.like;

@Component
public class CategoriaMySQLGateway implements CategoriaGateway {

    private final CategoriaRepository repository;

    public CategoriaMySQLGateway(CategoriaRepository repository) {
        this.repository = repository;
    }
    @Override
    public Categoria cadastrar(final Categoria categoria) {
     return save(categoria);
    }
    public Categoria atualizar(final Categoria categoria) {
        return save(categoria);
    }

    @Override
    public Optional<Categoria> findById(final CategoriaID anId) {
        return this.repository.findById(anId.getValue())
                .map(CategoriaJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Categoria> findAll(final SearchQuery aQuery) {
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
                pageResult.map(CategoriaJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public void deleteById(final CategoriaID anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public List<CategoriaID> existsByIds(Iterable<CategoriaID> poteIDs) {
        final var ids = StreamSupport.stream(poteIDs.spliterator(), false)
                .map(CategoriaID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(CategoriaID::de)
                .toList();
    }

    private Categoria save(final Categoria categoria) {
        return this.repository.save(CategoriaJpaEntity.de(categoria)).toAggregate();
    }

    private Specification<CategoriaJpaEntity> assembleSpecification(final String str) {
        final Specification<CategoriaJpaEntity> nomeLike = like("nome", str);
        final Specification<CategoriaJpaEntity> descricaoLike = like("descricao", str);
        return nomeLike.or(descricaoLike);
    }

}
