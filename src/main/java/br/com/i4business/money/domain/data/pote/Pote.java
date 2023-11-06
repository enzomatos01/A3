package br.com.i4business.money.domain.data.pote;

import br.com.i4business.money.domain.data.AggregateRoot;
import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.exception.NotificationException;
import br.com.i4business.money.domain.util.InstantUtils;
import br.com.i4business.money.domain.validation.ValidationHandler;
import br.com.i4business.money.domain.validation.handler.Notification;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Pote extends AggregateRoot<PoteID> {

    private String nome;
    private String descricao;
    private boolean ativo;
    private List<CategoriaID> categorias;
    private Instant criadoEm;
    private Instant alteradoEm;
    private Instant deletadoEm;

    protected Pote(PoteID poteID, String nome, String descricao, boolean ativo, List<CategoriaID> categorias, Instant criadoEm, Instant alteradoEm, Instant deletadoEm) {
        super(poteID);
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.categorias = categorias;
        this.criadoEm = criadoEm;
        this.alteradoEm = alteradoEm;
        this.deletadoEm = deletadoEm;
        selfValidate();
    }

    public static Pote newPote(final String nome, final String descricao, final boolean isAtivo) {
        final var id = PoteID.unique();
        var now = InstantUtils.now();
        final var deletadoEm = isAtivo ? null : now;
        return new Pote(id, nome, descricao, isAtivo, new ArrayList<>(),now, now, deletadoEm);
    }

    public static Pote com(
            final PoteID id,
            final String nome,
            final String descricao,
            final boolean ativo,
            final List<CategoriaID> categorias,
            final Instant criadoEm,
            final Instant alteradoEm,
            final Instant deletadoEm
    ) {
        return new Pote(
                id,
                nome,
                descricao,
                ativo,
                categorias,
                criadoEm,
                alteradoEm,
                deletadoEm
        );
    }

    public static Pote com(final Pote aPote) {
        return com(
                aPote.getId(),
                aPote.nome,
                aPote.descricao,
                aPote.isAtivo(),
                new ArrayList<>(aPote.categorias),
                aPote.criadoEm,
                aPote.alteradoEm,
                aPote.deletadoEm
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new PoteValidator(handler, this).validate();
    }

    public Pote atualizar(final String nome, final String descricao, final boolean isAtivo, final List<CategoriaID> categorias) {
        if (isAtivo) {
            ativar();
        } else {
            desativar();
        }

        this.nome = nome;
        this.descricao = descricao;
        this.categorias = new ArrayList<>(categorias != null ? categorias : Collections.emptyList());
        this.alteradoEm = Instant.now();
        selfValidate();
        return this;
    }

    public Pote ativar() {
        this.deletadoEm = null;
        this.ativo = true;
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public Pote desativar() {
        if (getDeletadoEm() == null) {
            this.deletadoEm = InstantUtils.now();
        }

        this.ativo = false;
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Instant getAlteradoEm() {
        return alteradoEm;
    }

    public Instant getDeletadoEm() {
        return deletadoEm;
    }

    public List<CategoriaID> getCategorias() {
        return Collections.unmodifiableList(categorias);
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate Pote", notification);
        }
    }

    public Pote addCategoria(final CategoriaID categoriaID) {
        if (categoriaID == null) {
            return this;
        }
        this.categorias.add(categoriaID);
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public Pote addCategorias(final List<CategoriaID> categorias) {
        if (categorias == null || categorias.isEmpty()) {
            return this;
        }
        this.categorias.addAll(categorias);
        this.alteradoEm = InstantUtils.now();
        return this;
    }

    public Pote removeCategoria(final CategoriaID categoriaID) {
        if (categoriaID == null) {
            return this;
        }
        this.categorias.remove(categoriaID);
        this.alteradoEm = InstantUtils.now();
        return this;
    }

}
