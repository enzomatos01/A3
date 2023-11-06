package br.com.i4business.money.infrastructure.categoria.persistence;

import br.com.i4business.money.domain.data.item.ItemID;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "categoria_item")
public class CategoriaItemJpaEntity {

    @EmbeddedId
    private CategoriaItemID id;

    @ManyToOne
    @MapsId("categoriaId")
    private CategoriaJpaEntity categoria;

    public CategoriaItemJpaEntity() {}

    private CategoriaItemJpaEntity(final CategoriaJpaEntity categoriaId, final ItemID itemId){
        this.id = CategoriaItemID.de(categoriaId.getId(), itemId.getValue());
        this.categoria = categoriaId;
    }

    public static CategoriaItemJpaEntity de(final CategoriaJpaEntity categoria, final ItemID itemId){
      return new CategoriaItemJpaEntity(categoria, itemId);
    }

    public CategoriaItemID getId() {
        return id;
    }

    public CategoriaItemJpaEntity setId(CategoriaItemID id) {
        this.id = id;
        return this;
    }

    public CategoriaJpaEntity getCategoria() {
        return categoria;
    }

    public CategoriaItemJpaEntity setCategoria(CategoriaJpaEntity categoria) {
        this.categoria = categoria;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CategoriaItemJpaEntity that = (CategoriaItemJpaEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
