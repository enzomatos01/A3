package br.com.i4business.money.application.pote.cadastrar;

import br.com.i4business.money.domain.data.pote.Pote;

public record CadastrarPoteResponse(
        String id
) {
    public static CadastrarPoteResponse de(final String anId) {
        return new CadastrarPoteResponse(anId);
    }

    public static CadastrarPoteResponse de(final Pote pote) {
        return new CadastrarPoteResponse(pote.getId().getValue());
    }
}
