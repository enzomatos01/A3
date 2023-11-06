package br.com.i4business.money.infrastructure.categoria.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoriaItemID implements Serializable {

    @Column(name = "categoria_id", nullable = false)
    private String categoriaId;

    @Column(name = "item_id", nullable = false)
    private String itemId;

    public CategoriaItemID(){}

    private CategoriaItemID(final String categoriaId, final String itemId) {
        this.categoriaId = categoriaId;
        this.itemId = itemId;
    }

    public static CategoriaItemID de(final String categoriaId, final String itemId){
        return new CategoriaItemID(categoriaId,itemId);
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public CategoriaItemID setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public CategoriaItemID setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CategoriaItemID that = (CategoriaItemID) o;
        return Objects.equals(getCategoriaId(), that.getCategoriaId()) && Objects.equals(getItemId(), that.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoriaId(), getItemId());
    }
}
