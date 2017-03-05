/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_aplicacao;

/**
 *
 * @author Mary
 */
public class ItemNotaFiscal implements Entidade {

    private int codigo;
    private float valorUnitario;
    private int quantidade;

    public ItemNotaFiscal(int codigo, float valorUnitario, int quantidade) {
        this.codigo = codigo;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
