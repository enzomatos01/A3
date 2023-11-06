package br.com.i4business.money.infrastructure.item;

import br.com.i4business.money.domain.data.item.Item;
import br.com.i4business.money.domain.data.item.ItemGateway;
import br.com.i4business.money.domain.data.item.ItemID;
import br.com.i4business.money.domain.pagination.Pagination;
import br.com.i4business.money.domain.pagination.SearchQuery;
import br.com.i4business.money.infrastructure.item.persistence.ItemJpaEntity;
import br.com.i4business.money.infrastructure.item.persistence.ItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static br.com.i4business.money.infrastructure.utils.SpecificationUtils.like;

@Component
public class ItemMySQLGateway implements ItemGateway {

    private final ItemRepository repository;

    public ItemMySQLGateway(ItemRepository repository) {
        this.repository = repository;
    }
    @Override
    public Item cadastrar(final Item item) {
     return save(item);
    }
    public Item atualizar(final Item item) {
        return save(item);
    }

    @Override
    public Optional<Item> findById(final ItemID anId) {
        return this.repository.findById(anId.getValue())
                .map(ItemJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Item> findAll(final SearchQuery aQuery) {
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
                pageResult.map(ItemJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public List<ItemID> existsByIds(Iterable<ItemID> itemIds) {
        final var ids = StreamSupport.stream(itemIds.spliterator(), false)
                .map(ItemID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(ItemID::de)
                .toList();
    }

    @Override
    public void deleteById(final ItemID anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    private Item save(final Item item) {
        return this.repository.save(ItemJpaEntity.de(item)).toAggregate();
    }

    private Specification<ItemJpaEntity> assembleSpecification(final String str) {
        final Specification<ItemJpaEntity> nomeLike = like("nome", str);
        final Specification<ItemJpaEntity> descricaoLike = like("descricao", str);
        return nomeLike.or(descricaoLike);
    }

}
