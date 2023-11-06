package br.com.i4business.money.domain.data.despesa;

import br.com.i4business.money.domain.data.Entity;
import br.com.i4business.money.domain.data.item.ItemID;
import br.com.i4business.money.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class DespesaItem {

    private DespesaID despesa;
    private ItemID item;
    private BigDecimal valor;

    public DespesaItem(DespesaID despesa, ItemID item, BigDecimal valor) {
        this.despesa = despesa;
        this.item = item;
        this.valor = valor;
    }

    public DespesaItem(ItemID item, BigDecimal valor) {
        this.item = item;
        this.valor = valor;
    }

    public static DespesaItem com(
            final DespesaID despesaID,
            final ItemID item,
            final BigDecimal valor
    ) {
        return new DespesaItem(
                despesaID,
                item,
                valor
        );
    }

    public DespesaID getDespesa() {
        return despesa;
    }

    public ItemID getItem() {
        return item;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
