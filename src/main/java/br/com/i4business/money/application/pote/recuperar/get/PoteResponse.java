package br.com.i4business.money.application.pote.recuperar.get;

import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.data.pote.Pote;
import br.com.i4business.money.domain.data.pote.PoteID;

import java.time.Instant;
import java.util.List;

public record PoteResponse(
        PoteID id,
        String nome,
        String descricao,
        boolean isAtivo,
        List<String> categorias,
        Instant criadoEm,
        Instant atualizadoEm,
        Instant deletadoEm
) {
    public static PoteResponse de(final Pote pote) {
        return new PoteResponse(
                pote.getId(),
                pote.getNome(),
                pote.getDescricao(),
                pote.isAtivo(),
                pote.getCategorias().stream()
                        .map(CategoriaID::getValue)
                        .toList(),
                pote.getCriadoEm(),
                pote.getAlteradoEm(),
                pote.getDeletadoEm()
        );
    }
}
