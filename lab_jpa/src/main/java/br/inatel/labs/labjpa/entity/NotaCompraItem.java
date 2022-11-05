package br.inatel.labs.labjpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class NotaCompraItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal valorCompraProduto;

    @NotNull
    private Integer quantidade;

    @NotNull
    @ManyToOne
    private NotaCompra notaCompra;

    @NotNull
    @ManyToOne
    private Produto produto;


    public BigDecimal getCalculoTotalItem(){
        return valorCompraProduto.multiply(BigDecimal.valueOf(quantidade));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorCompraProduto() {
        return valorCompraProduto;
    }

    public void setValorCompraProduto(BigDecimal valorCompraProduto) {
        this.valorCompraProduto = valorCompraProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public NotaCompra getNotaCompra() {
        return notaCompra;
    }

    public void setNotaCompra(NotaCompra notaCompra) {
        this.notaCompra = notaCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public NotaCompraItem(BigDecimal valorCompraProduto, Integer quantidade, NotaCompra notaCompra, Produto produto) {
        this.valorCompraProduto = valorCompraProduto;
        this.quantidade = quantidade;
        this.notaCompra = notaCompra;
        this.produto = produto;
    }

    public NotaCompraItem() {
    }

    @Override
    public String toString() {
        return "NotaCompraItem{" +
                "id=" + id +
                ", valorCompraProduto=" + valorCompraProduto +
                ", quantidade=" + quantidade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotaCompraItem)) return false;
        NotaCompraItem that = (NotaCompraItem) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
