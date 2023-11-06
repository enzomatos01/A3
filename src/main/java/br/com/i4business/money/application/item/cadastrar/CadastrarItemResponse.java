package br.com.i4business.money.application.item.cadastrar;

import br.com.i4business.money.domain.data.item.Item;

public record CadastrarItemResponse(
        String id
) {
    public static CadastrarItemResponse de(final String anId) {
        return new CadastrarItemResponse(anId);
    }

    public static CadastrarItemResponse de(final Item item) {
        return new CadastrarItemResponse(item.getId().getValue());
    }
}
