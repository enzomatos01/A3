package br.com.i4business.money.infrastructure.despesa.persistence;

import br.com.i4business.money.domain.data.despesa.DespesaID;
import br.com.i4business.money.domain.data.despesa.DespesaItem;
import br.com.i4business.money.domain.data.item.ItemID;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "despesaItem")
@Table(name = "despesa_item")
public class DespesaItemJpaEntity {

    @EmbeddedId
    private DespesaItemID id;

    @ManyToOne
    @MapsId("despesaId")
    private DespesaJpaEntity despesa;

    @Column(name = "valor")
    private BigDecimal valor;

    public DespesaItemJpaEntity() {}

    private DespesaItemJpaEntity(final DespesaJpaEntity despesaId, final ItemID itemId, final BigDecimal valor){
        this.id = DespesaItemID.de(despesaId.getId(), itemId.getValue());
        this.despesa = despesaId;
        this.valor = valor;
    }

    public static DespesaItemJpaEntity de(final DespesaJpaEntity despesa, final ItemID itemId, final  BigDecimal valor){
      return new DespesaItemJpaEntity(despesa, itemId, valor);
    }

    public static DespesaItemJpaEntity com(
            DespesaJpaEntity despesa,
            ItemID itemId,
            BigDecimal valor) {

        return new DespesaItemJpaEntity(despesa, itemId, valor);
    }

    public DespesaItemID getId() {
        return id;
    }

    public DespesaItemJpaEntity setId(DespesaItemID id) {
        this.id = id;
        return this;
    }

    public DespesaJpaEntity getDespesa() {
        return despesa;
    }

    public DespesaItemJpaEntity setDespesa(DespesaJpaEntity despesa) {
        this.despesa = despesa;
        return this;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public DespesaItemJpaEntity setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public DespesaItem toAggregate() {
        return DespesaItem.com(
                DespesaID.de(getId().getDespesaId()),
                ItemID.de(getId().getItemId()),
                getValor()
        );
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DespesaItemJpaEntity that = (DespesaItemJpaEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
