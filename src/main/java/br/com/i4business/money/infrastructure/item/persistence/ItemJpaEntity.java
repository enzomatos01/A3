package br.com.i4business.money.infrastructure.item.persistence;

import br.com.i4business.money.domain.data.item.Item;
import br.com.i4business.money.domain.data.item.ItemID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity(name = "Item")
@Table(name = "item")
public class ItemJpaEntity {

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

//    @ManyToOne
//    @JoinColumn(name = "categoria_id")
//    private CategoriaJpaEntity categoria;

    public ItemJpaEntity() {
    }

    private ItemJpaEntity(final String id, final String nome, final String descricao, final boolean ativo,  final Instant criadoEm, final Instant atualizadoEm, final Instant deletadoEm) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
//        this.categoria = categoria;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.deletadoEm = deletadoEm;
    }

    public static ItemJpaEntity de(final Item item) {
        return new ItemJpaEntity(
                item.getId().getValue(),
                item.getNome(),
                item.getDescricao(),
                item.isAtivo(),
//                CategoriaJpaEntity.de(item.getCategoria()),
                item.getCriadoEm(),
                item.getAlteradoEm(),
                item.getDeletadoEm()
        );
    }
    public Item toAggregate() {

        return Item.com(
                ItemID.de(getId()),
                getNome(),
                getDescricao(),
                isAtivo(),
                getCriadoEm(),
                getAtualizadoEm(),
                getDeletadoEm()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

//    public CategoriaJpaEntity getCategoria() { return categoria; }
//    public void setCategoria(CategoriaJpaEntity categoria) { this.categoria = categoria; }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(Instant atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public Instant getDeletadoEm() {
        return deletadoEm;
    }

    public void setDeletadoEm(Instant deletadoEm) {
        this.deletadoEm = deletadoEm;
    }
}
