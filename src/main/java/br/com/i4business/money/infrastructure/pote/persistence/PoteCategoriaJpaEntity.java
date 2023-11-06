package br.com.i4business.money.infrastructure.pote.persistence;

import br.com.i4business.money.domain.data.categoria.CategoriaID;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pote_categoria")
public class PoteCategoriaJpaEntity {

    @EmbeddedId
    private PoteCategoriaID id;

    @ManyToOne
    @MapsId("poteId")
    private PoteJpaEntity pote;



    public PoteCategoriaJpaEntity() {}

    private PoteCategoriaJpaEntity(final PoteJpaEntity poteId, final CategoriaID categoriaId) {
        this.id = PoteCategoriaID.from(poteId.getId(), categoriaId.getValue());
        this.pote = poteId;
    }

    public static PoteCategoriaJpaEntity from(final PoteJpaEntity pote, final CategoriaID categoryId) {
        return new PoteCategoriaJpaEntity(pote, categoryId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PoteCategoriaJpaEntity that = (PoteCategoriaJpaEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public PoteCategoriaID getId() {
        return id;
    }

    public PoteCategoriaJpaEntity setId(PoteCategoriaID id) {
        this.id = id;
        return this;
    }

    public PoteJpaEntity getPote() {
        return pote;
    }

    public PoteCategoriaJpaEntity setGenre(PoteJpaEntity pote) {
        this.pote = pote;
        return this;
    }
}
