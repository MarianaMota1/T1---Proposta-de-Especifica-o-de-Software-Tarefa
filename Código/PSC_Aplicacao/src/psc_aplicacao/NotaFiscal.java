/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_aplicacao;

import java.util.Date;

/**
 *
 * @author Mary
 */
public class NotaFiscal implements Entidade {

    private int codigo;
    private Date data;
    private float valorTotal;
    private String descricao;

    public NotaFiscal() {
    }

    public NotaFiscal(int codigo, Date data, float valorTotal, String descricao) {
        this.codigo = codigo;
        this.data = data;
        this.valorTotal = valorTotal;
        this.descricao = descricao;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) throws ErroValidacao {
        if (descricao.length() > 100) {
            throw new ErroValidacao("O atributo descrição deve ter no máximo 100 caracteres!");
        }
        this.descricao = descricao;
    }

}
