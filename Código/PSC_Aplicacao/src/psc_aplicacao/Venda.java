
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package psc_aplicacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mary
 */
public class Venda implements Entidade{
    private int codigo;
    private Cliente cliente;
    private Date data;
    private BigDecimal valorTotal;
    private List<VendaItem> itens;
    
    public Venda() {
        itens = new ArrayList<>();
    }
    
    public void addItem(VendaItem item){
        if(!itens.contains(item)) {
            itens.add(item);
            this.valorTotal = this.valorTotal.add( 
                    item.getProduto().getPrecoUnitario().multiply(  new BigDecimal( item.getQuantidade()  )   )  );
        }
    }
    
    /**
     *
     * @param item
     */
    public void removeItem(VendaItem item){
        if(itens.contains(item)){
            itens.remove(item);
        this.valorTotal = this.valorTotal.subtract(
                    item.getProduto().getPrecoUnitario().multiply(  new BigDecimal( item.getQuantidade()  )   )  );
        }
    }
    
    

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<VendaItem> getItens() {
        return itens;
    }

    public void setItens(List<VendaItem> itens) {
        this.itens = itens;
    }
    

    public Venda(int codigo, Cliente cliente, Date data, BigDecimal valorTotal, List<VendaItem> itens) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.data = data;
        this.valorTotal = valorTotal;
        itens = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.codigo;
        hash = 53 * hash + Objects.hashCode(this.cliente);
        hash = 53 * hash + Objects.hashCode(this.data);
        hash = 53 * hash + Objects.hashCode(this.valorTotal);
        hash = 53 * hash + Objects.hashCode(this.itens);
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
        final Venda other = (Venda) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.valorTotal, other.valorTotal)) {
            return false;
        }
        return Objects.equals(this.itens, other.itens);
    }

    @Override
    public String toString() {
        return "Venda{" + "codigo=" + codigo + ", cliente=" + cliente + ", data=" + data + ", valorTotal=" + valorTotal + ", itens=" + itens + '}';
    }   
    
}