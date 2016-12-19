
import java.util.Date;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mary
 */
public class NotaFiscal {
    
    private int id;
    private Date data;
    private float valorTotal;
    private String descricao;

    public NotaFiscal() {
    }

    public NotaFiscal(int id, Date data, float valorTotal, String descricao) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if(descricao.length() > 100)
            throw new ErroValidacao("O atributo descrição deve ter no máximo 100 caracteres!");
        this.descricao = descricao;
    }
    
    
    
    
}
