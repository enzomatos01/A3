package br.com.i4business.money.application.item.atualizar;

import br.com.i4business.money.domain.data.item.Item;

public record AtualizarItemResponse(
        String id
) {
    public static AtualizarItemResponse de(final String anId) {
        return new AtualizarItemResponse(anId);
    }

    public static AtualizarItemResponse de(final Item item) {
        return new AtualizarItemResponse(item.getId().getValue());
    }
}
