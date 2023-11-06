package br.com.i4business.money.application.pote.atualizar;

import java.util.List;

public record AtualizarPoteCommand(
        String id,
        String nome,
        String descricao,
        boolean isAtivo,
        List<String> categorias
) {
    public static AtualizarPoteCommand com(
            final String id,
            final String nome,
            final String descricao,
            final Boolean isAtivo,
            final List<String> categorias
    ) {
        return new AtualizarPoteCommand(id, nome, descricao, isAtivo != null ? isAtivo : true, categorias);
    }
}
