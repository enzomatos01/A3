package br.com.i4business.money.application.pote.atualizar;

import br.com.i4business.money.domain.data.pote.Pote;

public record AtualizarPoteResponse(
        String id
) {
    public static AtualizarPoteResponse de(final String anId) {
        return new AtualizarPoteResponse(anId);
    }

    public static AtualizarPoteResponse de(final Pote pote) {
        return new AtualizarPoteResponse(pote.getId().getValue());
    }
}
