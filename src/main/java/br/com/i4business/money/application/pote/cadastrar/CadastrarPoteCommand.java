package br.com.i4business.money.application.pote.cadastrar;

import java.util.List;

public record CadastrarPoteCommand(
        String nome,
        String descricao,
        boolean isAtivo,
        List<String> categorias
) {

    public static CadastrarPoteCommand com(
            final String nome,
            final String descricao,
            final Boolean isAtivo,
            final List<String> categorias
    ) {
        return new CadastrarPoteCommand(nome, descricao, isAtivo != null ? isAtivo : true, categorias);
    }
}
