/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_apresentacao_desktop;

import psc_aplicacao.ClienteRepositorio;
import psc_aplicacao.FornecedorRepositorio;
import psc_aplicacao.FuncionarioRepositorio;
import psc_aplicacao.ProdutoRepositorio;
import psc_aplicacao.VendaRepositorio;
import psc_persistencia.ClienteDAO;
import psc_persistencia.FornecedorDAO;
import psc_persistencia.FuncionarioDAO;
import psc_persistencia.ProdutoDAO;
import psc_persistencia.VendaDAO;

/**
 *
 * @author Mary
 */
public class GerenciadorReferencias {

    private static ClienteRepositorio daoCliente;

    public static ClienteRepositorio getCliente() {
        if (daoCliente == null) {
            daoCliente = new ClienteDAO();
        }

        return daoCliente;
    }

    private static FuncionarioRepositorio daoFuncionario;

    public static FuncionarioRepositorio getFuncionario() {
        if (daoFuncionario == null) {
            daoFuncionario = new FuncionarioDAO();
        }

        return daoFuncionario;

    }

    private static FornecedorRepositorio daoFornecedor;

    public static FornecedorRepositorio getFornecedor() {
        if (daoFornecedor == null) {
            daoFornecedor = new FornecedorDAO();
        }

        return daoFornecedor;

    }

    private static ProdutoRepositorio daoProduto;

    public static ProdutoRepositorio getProduto() {
        if (daoProduto == null) {
            daoProduto = new ProdutoDAO();
        }

        return daoProduto;
    }

    private static VendaRepositorio daoVenda;

    public static VendaRepositorio getVenda() {
        if (daoVenda == null) {
            daoVenda = new VendaDAO();
        }

        return daoVenda;
    }

}
