package br.com.i4business.money.application.despesa.recuperar.get;

import br.com.i4business.money.domain.data.categoria.Categoria;
import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.data.despesa.Despesa;
import br.com.i4business.money.domain.data.despesa.DespesaID;
import br.com.i4business.money.domain.data.item.ItemID;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record GetDespesaByIdResponse(
        DespesaID id,
        String nome,
        List<GetDespesaItemResponse> itens,
        boolean isAtivo,
        Instant criadoEm,
        Instant atualizadoEm,
        Instant deletadoEm
) {
    public static GetDespesaByIdResponse de(final Despesa aDespesa) {
        return new GetDespesaByIdResponse(
                aDespesa.getId(),
                aDespesa.getNome(),
                aDespesa.getItens().stream()
                        .map(GetDespesaItemResponse::fromDespesaItem)
                        .toList(),
                aDespesa.isAtivo(),
                aDespesa.getCriadoEm(),
                aDespesa.getAlteradoEm(),
                aDespesa.getDeletadoEm()
        );
    }
}
