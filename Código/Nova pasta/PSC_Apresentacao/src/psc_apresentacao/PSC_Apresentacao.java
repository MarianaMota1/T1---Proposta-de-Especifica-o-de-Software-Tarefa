/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_apresentacao;

import psc_aplicacao.Funcionario;
import psc_aplicacao.FuncionarioRepositorio;
import psc_apresentacao_desktop.GerenciadorReferencias;


/**
 *
 * @author Mary
 */
public class PSC_Apresentacao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FuncionarioRepositorio dao = GerenciadorReferencias.getFuncionario();
        System.out.println("" + dao.Buscar(new Funcionario()).size());
    }

}
