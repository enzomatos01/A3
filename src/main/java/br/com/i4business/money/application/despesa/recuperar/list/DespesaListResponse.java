package br.com.i4business.money.application.despesa.recuperar.list;

import br.com.i4business.money.domain.data.despesa.Despesa;
import br.com.i4business.money.domain.data.despesa.DespesaID;
import br.com.i4business.money.domain.data.item.ItemID;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record DespesaListResponse(
        DespesaID id,
        String nome,
        //List<String> itens,
        boolean isAtivo,
        Instant criadoEm,
        Instant atualizadoEm,
        Instant deletadoEm
) {
    public static DespesaListResponse de(final Despesa aDespesa) {
        return new DespesaListResponse(
                aDespesa.getId(),
                aDespesa.getNome(),
                //aDespesa.getItens().stream().map(ItemID::getValue).toList(),
                aDespesa.isAtivo(),
                aDespesa.getCriadoEm(),
                aDespesa.getAlteradoEm(),
                aDespesa.getDeletadoEm()
        );
    }
}
