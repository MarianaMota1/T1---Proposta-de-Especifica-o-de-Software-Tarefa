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
public class MeioPagamento implements Entidade {

    private int codigo;
    private String meioPagamento;
    private Venda venda;

    public MeioPagamento(int codigo, String meioPagamento) {
        this.codigo = codigo;
        this.meioPagamento = meioPagamento;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

}
