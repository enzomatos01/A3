package br.com.i4business.money.application.despesa.recuperar.get;

import br.com.i4business.money.domain.data.despesa.DespesaID;
import br.com.i4business.money.domain.data.despesa.DespesaItem;
import br.com.i4business.money.domain.data.item.ItemID;

import java.math.BigDecimal;

public record GetDespesaItemResponse(
        DespesaID despesaId,
        ItemID itemId,
        BigDecimal valor
) {
    public static GetDespesaItemResponse fromDespesaItem(final DespesaItem despesaItem) {
        return new GetDespesaItemResponse(
                despesaItem.getDespesa(),
                despesaItem.getItem(),
                despesaItem.getValor()
        );
    }
}
