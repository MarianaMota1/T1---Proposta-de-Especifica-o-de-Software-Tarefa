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
public class MeioPagamento {
    
    private int id;
    private String meioPagemento;

    public MeioPagamento(int id, String meioPagemento) {
        this.id = id;
        this.meioPagemento = meioPagemento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeioPagemento() {
        return meioPagemento;
    }

    public void setMeioPagemento(String meioPagemento) {
        this.meioPagemento = meioPagemento;
    }
    
    
    
}
