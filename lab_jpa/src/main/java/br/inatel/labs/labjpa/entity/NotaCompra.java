package br.inatel.labs.labjpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class NotaCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate dataEmissao;

    @ManyToOne
    @NotNull
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "notaCompra")
    private List<NotaCompraItem> listaNotaCompraItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<NotaCompraItem> getListaNotaCompraItem() {
        return listaNotaCompraItem;
    }

    public void setListaNotaCompraItem(List<NotaCompraItem> listaNotaCompraItem) {
        this.listaNotaCompraItem = listaNotaCompraItem;
    }

    public NotaCompra(LocalDate dataEmissao, Fornecedor fornecedor) {
        this.dataEmissao = dataEmissao;
        this.fornecedor = fornecedor;
    }

    public NotaCompra() {
    }

    @Override
    public String toString() {
        return "NotaCompra{" +
                "id=" + id +
                ", dataEmissao=" + dataEmissao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotaCompra)) return false;
        NotaCompra that = (NotaCompra) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public BigDecimal getCalculoTotalNota(){
        return this.listaNotaCompraItem.stream()
                .map(NotaCompraItem::getCalculoTotalItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
