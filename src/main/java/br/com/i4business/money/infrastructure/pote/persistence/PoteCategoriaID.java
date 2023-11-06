package br.com.i4business.money.infrastructure.pote.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PoteCategoriaID implements Serializable {

    @Column(name = "pote_id", nullable = false)
    private String poteId;

    @Column(name = "categoria_id", nullable = false)
    private String categoriaId;

    public PoteCategoriaID() {}

    private PoteCategoriaID(final String poteId, final String categoriaId) {
        this.poteId = poteId;
        this.categoriaId = categoriaId;
    }

    public static PoteCategoriaID from(final String poteId, final String categoriaId) {
        return new PoteCategoriaID(poteId, categoriaId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PoteCategoriaID that = (PoteCategoriaID) o;
        return Objects.equals(getPoteId(), that.getPoteId()) && Objects.equals(getCategoriaId(), that.getCategoriaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoteId(), getCategoriaId());
    }

    public String getPoteId() {
        return poteId;
    }

    public PoteCategoriaID setPoteId(String poteId) {
        this.poteId = poteId;
        return this;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public PoteCategoriaID setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
        return this;
    }
}
