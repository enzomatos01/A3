package br.com.i4business.money.infrastructure.pote.persistence;

import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.data.pote.Pote;
import br.com.i4business.money.domain.data.pote.PoteID;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity(name = "Pote")
@Table(name = "pote")
public class PoteJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", length = 4000)
    private String descricao;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @OneToMany(mappedBy = "pote", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Set<PoteCategoriaJpaEntity> categorias;

    @Column(name = "criado_em", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant criadoEm;

    @Column(name = "atualizado_em", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant atualizadoEm;

    @Column(name = "deletado_em", columnDefinition = "DATETIME(6)")
    private Instant deletadoEm;

    public PoteJpaEntity() {
    }

    private PoteJpaEntity(final String id, final String nome, final String descricao, final boolean ativo, final Instant criadoEm, final Instant atualizadoEm, final Instant deletadoEm) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.categorias = new HashSet<>();
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.deletadoEm = deletadoEm;
    }

    public static PoteJpaEntity de(final Pote pote) {
        return new PoteJpaEntity(
                pote.getId().getValue(),
                pote.getNome(),
                pote.getDescricao(),
                pote.isAtivo(),
                pote.getCriadoEm(),
                pote.getAlteradoEm(),
                pote.getDeletadoEm()
        );
    }

    public Pote toAggregate() {
        return Pote.com(
                PoteID.de(getId()),
                getNome(),
                getDescricao(),
                isAtivo(),
                getCategoriaIDs(),
                getCriadoEm(),
                getAtualizadoEm(),
                getDeletadoEm()
        );
    }

    private void addCategoria(final CategoriaID anId) {
        this.categorias.add(PoteCategoriaJpaEntity.from(this, anId));
    }

    private void removeCategoria(final CategoriaID anId) {
        this.categorias.remove(PoteCategoriaJpaEntity.from(this, anId));
    }


    public List<CategoriaID> getCategoriaIDs() {
        return getCategorias().stream()
                .map(it -> CategoriaID.de(it.getId().getCategoriaId()))
                .toList();
    }

    public String getId() {
        return id;
    }

    public PoteJpaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public PoteJpaEntity setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public PoteJpaEntity setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public PoteJpaEntity setAtivo(boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public Set<PoteCategoriaJpaEntity> getCategorias() {
        return categorias;
    }

    public PoteJpaEntity setCategorias(Set<PoteCategoriaJpaEntity> categorias) {
        this.categorias = categorias;
        return this;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public PoteJpaEntity setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
        return this;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public PoteJpaEntity setAtualizadoEm(Instant atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
        return this;
    }

    public Instant getDeletadoEm() {
        return deletadoEm;
    }

    public PoteJpaEntity setDeletadoEm(Instant deletadoEm) {
        this.deletadoEm = deletadoEm;
        return this;
    }
}
