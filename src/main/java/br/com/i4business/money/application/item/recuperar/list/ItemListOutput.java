package br.com.i4business.money.application.item.recuperar.list;

import br.com.i4business.money.domain.data.item.Item;
import br.com.i4business.money.domain.data.item.ItemID;

import java.time.Instant;

public record ItemListOutput(
        ItemID id,
        String nome,
        String descricao,
        boolean isAtivo,
        Instant criadoEm,
        Instant atualizadoEm,
        Instant deletadoEm
) {
    public static ItemListOutput de(final Item item) {
        return new ItemListOutput(
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
