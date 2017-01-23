/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_apresentacao;

import psc_aplicacao.FuncionarioRepositorio;
import psc_aplicacao.ProdutoRepositorio;
import psc_persistencia.FuncionarioDAO;
import psc_persistencia.ProdutoDAO;

/**
 *
 * @author Mary
 */
public class GerenciadorReferencias {

    private static FuncionarioRepositorio daoFuncionario;

    public static FuncionarioRepositorio getFuncionario() {
        if (daoFuncionario == null) {
            daoFuncionario = new FuncionarioDAO();
        }
        return daoFuncionario;
    }

    private static ProdutoRepositorio daoProduto;

    public static ProdutoRepositorio getProduto() {
        if (daoProduto == null) {
            daoProduto = new ProdutoDAO();
        }
        return daoProduto;
    }

}
