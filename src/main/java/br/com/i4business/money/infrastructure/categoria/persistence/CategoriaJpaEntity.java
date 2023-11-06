package br.com.i4business.money.infrastructure.categoria.persistence;

import br.com.i4business.money.domain.data.categoria.Categoria;
import br.com.i4business.money.domain.data.categoria.CategoriaID;
import br.com.i4business.money.domain.data.item.ItemID;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity(name = "Categoria")
@Table(name = "categoria")
public class CategoriaJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", length = 4000)
    private String descricao;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Column(name = "criado_em", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant criadoEm;

    @Column(name = "atualizado_em", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant atualizadoEm;

    @Column(name = "deletado_em", columnDefinition = "DATETIME(6)")
    private Instant deletadoEm;

    @OneToMany(mappedBy = "categoria", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Set<CategoriaItemJpaEntity> itens;

    public CategoriaJpaEntity() {
    }

    private CategoriaJpaEntity(final String id) {
        this.id = id;
    }

    private CategoriaJpaEntity(final String id,
                               final String nome,
                               final String descricao,
                               final boolean ativo,
                               final Instant criadoEm,
                               final Instant atualizadoEm,
                               final Instant deletadoEm) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.deletadoEm = deletadoEm;
        this.itens = new HashSet<>(3);
    }

    public static CategoriaJpaEntity de(final Categoria categoria) {
        final var aCategoria =  new CategoriaJpaEntity(
                categoria.getId().getValue(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.isAtivo(),
                categoria.getCriadoEm(),
                categoria.getAlteradoEm(),
                categoria.getDeletadoEm()
        );

        categoria.getItens().forEach(aCategoria::addItem);

        return aCategoria;
    }

    public static CategoriaJpaEntity de(final CategoriaID categoriaID) {
        return new CategoriaJpaEntity(categoriaID.getValue());
    }

    public Categoria toAggregate() {
        return Categoria.com(
                CategoriaID.de(getId()),
                getNome(),
                getDescricao(),
                isAtivo(),
                getItemIDs(),
                getCriadoEm(),
                getAtualizadoEm(),
                getDeletadoEm()
        );
    }

    public List<ItemID> getItemIDs() {
        return getItens().stream()
                .map(it -> ItemID.de(it.getId().getItemId()))
                .toList();
    }

    private void addItem(final ItemID anId) {
        this.itens.add(CategoriaItemJpaEntity.de(this, anId));
    }

    private void removeItem(final ItemID anId) {
        this.itens.remove(CategoriaItemJpaEntity.de(this, anId));
    }

    public String getId() {
        return id;
    }

    public CategoriaJpaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaJpaEntity setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaJpaEntity setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public CategoriaJpaEntity setAtivo(boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public CategoriaJpaEntity setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
        return this;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public CategoriaJpaEntity setAtualizadoEm(Instant atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
        return this;
    }

    public Instant getDeletadoEm() {
        return deletadoEm;
    }

    public CategoriaJpaEntity setDeletadoEm(Instant deletadoEm) {
        this.deletadoEm = deletadoEm;
        return this;
    }

    public Set<CategoriaItemJpaEntity> getItens() {
        return itens;
    }

    public CategoriaJpaEntity setItens(Set<CategoriaItemJpaEntity> itens) {
        this.itens = itens;
        return this;
    }
}
