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
        setConsultaAbrir("select codigo,nome,cpf,rg,funcao,senha from funcionario where codigo = ?");
        setConsultaApagar("DELETE FROM funcionario WHERE codigo = ?");
        setConsultaInserir("INSERT INTO funcionario(nome,cpf,rg,funcao,senha) VALUES(?,?,?,?,?)");
        setConsultaAlterar("UPDATE funcionario SET nome = ?, cpf = ?,"
                + "rg = ?,nome = ?,funcao= ? ,senha = ?"
                + "WHERE codigo = ?");
        setConsultaBusca("select codigo,nome,cpf,rg,funcao,senha from funcionario ");
        setConsultaUltimoCodigo("select max(codigo) from funcionario where nome = ? and cpf = ? and "
                + "rg = ? and funcao = ? and senha = ?");
    }

    /**
     *
     * @param resultado
     * @return
     */
    @Override
    protected Funcionario preencheObjeto(ResultSet resultado) {
        Funcionario tmp = new Funcionario();
        try {
            tmp.setCodigo(resultado.getInt(1));
            tmp.setNome(resultado.getString(2));
            tmp.setCpf(resultado.getString(3));
            tmp.setRg(resultado.getString(4));
            tmp.setFuncao(resultado.getString(5));
            tmp.setSenha(resultado.getString(6));
        } catch (SQLException | ErroValidacao ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    /**
     * @param sql
     * @param obj
     */
    @Override
    protected void preencheConsulta(PreparedStatement sql, Funcionario obj) {
        try {
            sql.setString(1, obj.getNome());
            sql.setString(2, obj.getCpf());
            sql.setString(3, obj.getRg());
            sql.setString(4, obj.getFuncao());
            sql.setString(5, obj.getSenha());
            if (obj.getCodigo() > 0) {
                sql.setInt(6, obj.getCodigo());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param codigo
     * @return
     */
    @Override
    public Funcionario Abrir(int codigo) {
        try {
            PreparedStatement sql = conn.prepareStatement("select codigo,nome,cpf,rg,funcao,senha "
                    + "from funcionario where codigo = ?");
            sql.setInt(1, codigo);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                return preencheObjeto(resultado);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * @param filtro
     */
    @Override
    protected void preencheFiltros(Funcionario filtro) {
        if (filtro.getCodigo() > 0) {
            adicionarFiltro("codigo", "=");
        }
        if (filtro.getNome() != null) {
            adicionarFiltro("nome", " like ");
        }
        if (filtro.getCpf() != null) {
            adicionarFiltro("cpf", "=");
        }
        if (filtro.getRg() != null) {
            adicionarFiltro("rg", "=");
        }
        if (filtro.getFuncao() != null) {
            adicionarFiltro("funcao", "=");
        }
        if (filtro.getSenha() != null) {
            adicionarFiltro("senha", "=");
        }
    }

    /**
     * @param sql
     * @param filtro
     */
    @Override
    protected void preencheParametros(PreparedStatement sql, Funcionario filtro) {
        try {
            int cont = 1;
            if (filtro.getCodigo() > 0) {
                sql.setInt(cont, filtro.getCodigo());
                cont++;
            }
            if (filtro.getNome() != null) {
                sql.setString(cont, filtro.getNome() + "%");
                cont++;
            }
            if (filtro.getCpf() != null) {
                sql.setString(cont, filtro.getCpf());
                cont++;
            }
            if (filtro.getRg() != null) {
                sql.setString(cont, filtro.getRg());
                cont++;
            }
            if (filtro.getFuncao() != null) {
                sql.setString(cont, filtro.getFuncao());
                cont++;
            }
            if (filtro.getSenha() != null) {
                sql.setString(cont, filtro.getSenha());
                cont++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
