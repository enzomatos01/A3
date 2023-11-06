package br.com.i4business.money.application.item.recuperar.get;

import br.com.i4business.money.domain.data.item.Item;
import br.com.i4business.money.domain.data.item.ItemID;

import java.time.Instant;

public record GetItemByIdResponse(
        ItemID id,
        String nome,
        String descricao,
        boolean isAtivo,
        Instant criadoEm,
        Instant atualizadoEm,
        Instant deletadoEm
) {
    public static GetItemByIdResponse de(final Item item) {
        return new GetItemByIdResponse(
                item.getId(),
                item.getNome(),
                item.getDescricao(),
                item.isAtivo(),
                item.getCriadoEm(),
                item.getAlteradoEm(),
                item.getDeletadoEm()
        );
    }
}
