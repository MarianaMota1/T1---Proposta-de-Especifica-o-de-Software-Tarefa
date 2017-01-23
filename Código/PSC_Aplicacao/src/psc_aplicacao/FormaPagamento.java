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
public class FormaPagamento {

    private int codigo;
    private String tipoPagamento;

    public FormaPagamento(int codigo, String tipoPagamento) {
        this.codigo = codigo;
        this.tipoPagamento = tipoPagamento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

}
