package br.com.i4business.money.infrastructure.despesa.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class DespesaItemID implements Serializable {

    @Column(name = "despesa_id", nullable = false)
    private String despesaId;

    @Column(name = "item_id", nullable = false)
    private String itemId;

    public DespesaItemID(){}

    private DespesaItemID(final String despesaId, final String itemId) {
        this.despesaId = despesaId;
        this.itemId = itemId;
    }

    public static DespesaItemID de(final String despesaId, final String itemId){
        return new DespesaItemID(despesaId,itemId);
    }

    public static DespesaItemID com(String despesaId, String itemId) {
        return new DespesaItemID(despesaId, itemId);
    }

    public String getDespesaId() {
        return despesaId;
    }

    public DespesaItemID setDespesaId(String despesaId) {
        this.despesaId = despesaId;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public DespesaItemID setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DespesaItemID that = (DespesaItemID) o;
        return Objects.equals(getDespesaId(), that.getDespesaId()) && Objects.equals(getItemId(), that.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDespesaId(), getItemId());
    }
}
