package br.com.i4business.money.application.pote.recuperar.list;

import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.data.pote.Pote;
import br.com.i4business.money.domain.data.pote.PoteID;

import java.time.Instant;
import java.util.List;

public record PoteListResponse(
        PoteID id,
        String nome,
        String descricao,
        boolean isAtivo,
        List<String> categorias,
        Instant criadoEm,
        Instant atualizadoEm,
        Instant deletadoEm

) {
    public static PoteListResponse de(final Pote pote) {
        return new PoteListResponse(
                pote.getId(),
                pote.getNome(),
                pote.getDescricao(),
                pote.isAtivo(),
                pote.getCategorias().stream().map(CategoriaID::getValue).toList(),
                pote.getCriadoEm(),
                pote.getAlteradoEm(),
                pote.getDeletadoEm()
        );
    }
}
