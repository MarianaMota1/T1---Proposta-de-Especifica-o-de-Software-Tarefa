

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_aplicacao;

import java.util.Objects;
/**
 *
 * @author Mary
 */
public class VendaItem implements Entidade{
    private int codigo;
    private Venda venda;
    private Produto produto;
    private int quantidade;

    public VendaItem() {
    }

    public VendaItem(int codigo, Venda venda, Produto produto, int quantidade) {
        this.codigo = codigo;
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.codigo;
        hash = 43 * hash + Objects.hashCode(this.venda);
        hash = 43 * hash + Objects.hashCode(this.produto);
        hash = 43 * hash + this.quantidade;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VendaItem other = (VendaItem) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        return Objects.equals(this.produto, other.produto);
    }

    @Override
    public String toString() {
        return "VendaItem{" + "codigo=" + codigo + ", venda=" + venda + ", produto=" + produto + ", quantidade=" + quantidade + '}';
    }
     
    
}
