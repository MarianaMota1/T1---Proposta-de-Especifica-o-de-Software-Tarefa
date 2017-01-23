/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import psc_aplicacao.ErroValidacao;
import psc_aplicacao.Funcionario;
import psc_aplicacao.FuncionarioRepositorio;

/**
 *
 * @author Mary
 */
public class FuncionarioDAO extends DAOGenerico<Funcionario> implements FuncionarioRepositorio {

    public FuncionarioDAO() {
        setConsultaAbrir("select codigo, cpf, rg, nome, funcao, senha from funcionario where id = ?");
        setConsultaApagar("delete from funcionario where id = ?");
        setConsultaInserir("insert into funcionario(cpf, rg, nome, funcao, senha) values(?,?,?,?,?)");
        setConsultaAlterar("update funcionario set cpf = ?, rg = ?, nome = ?, funcao = ?, senha = ? where id = ?");
        setConsultaBusca("select codigo, cpf, rg, nome, funcao, senha from funcionario");
    }

    @Override
    protected Funcionario preencheObjeto(ResultSet resultado) {
        try {
            Funcionario tmp = new Funcionario();
            tmp.setCodigo(resultado.getInt(1));
            tmp.setCpf(resultado.getString(2));
            tmp.setRg(resultado.getString(3));
            tmp.setNome(resultado.getString(4));
            tmp.setFuncao(resultado.getString(5));
            tmp.setSenha(resultado.getString(6));
            return tmp;
        } catch (SQLException | ErroValidacao ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void preencheConsulta(PreparedStatement sql, Funcionario obj) {
        try {
            sql.setString(1, obj.getCpf());
            sql.setString(2, obj.getRg());
            sql.setString(3, obj.getNome());
            sql.setString(4, obj.getFuncao());
            sql.setString(5, obj.getSenha());
            if (obj.getCodigo() > 0) {
                sql.setInt(6, obj.getCodigo());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void preencheFiltros(Funcionario filtro) {
        if (filtro.getCodigo() > 0) {
            adicionarFiltro("codigo", "=");
        }
        if (filtro.getCpf() != null) {
            adicionarFiltro("cpf", " like ");
        }
        if (filtro.getRg() != null) {
            adicionarFiltro("rg", " like ");
        }
        if (filtro.getNome() != null) {
            adicionarFiltro("nome", " like ");
        }
        if (filtro.getFuncao() != null) {
            adicionarFiltro("funcao", " like ");
        }
        if (filtro.getSenha() != null) {
            adicionarFiltro("senha", " like ");
        }
    }

    @Override
    protected void preencheParametros(PreparedStatement sql, Funcionario filtro) {
        try {
            int cont = 1;
            if (filtro.getCodigo() > 0) {
                sql.setInt(cont, filtro.getCodigo());
                cont++;
            }
        } catch (Exception ex) {
        }
    }

}
