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
import psc_aplicacao.Cliente;
import psc_aplicacao.ClienteRepositorio;
import psc_aplicacao.ErroValidacao;

/**
 *
 * @author Mary
 */
public class ClienteDAO extends DAOGenerico<Cliente> implements ClienteRepositorio {

    public ClienteDAO() {
        setConsultaAbrir("select codigo,nome,cpf,rg,endereco,telefone from cliente where codigo = ?");
        setConsultaApagar("DELETE FROM cliente WHERE codigo = ? ");
        setConsultaInserir("INSERT INTO cliente(nome,cpf,rg,endereco,telefone) VALUES(?,?,?,?,?)");
        setConsultaAlterar("UPDATE cliente SET nome = ?, "
                + "cpf = ?, rg = ?,endereco = ?,telefone= ? "
                + "WHERE codigo = ?");
        setConsultaBusca("select codigo,nome,cpf,rg,endereco,telefone from cliente ");
        setConsultaUltimoCodigo("select max(codigo) from cliente where nome = ? and cpf = ? and rg = ? "
                + "and endereco = ? and telefone = ?");
    }

    /**
     *
     * @param resultado
     * @return
     */
    @Override
    protected Cliente preencheObjeto(ResultSet resultado) {
        Cliente tmp = new Cliente();
        try {
            tmp.setCodigo(resultado.getInt(1));
            tmp.setNome(resultado.getString(2));
            tmp.setCpf(resultado.getString(3));
            tmp.setRg(resultado.getString(4));
            tmp.setEndereco(resultado.getString(5));
            tmp.setTelefone(resultado.getString(6));
        } catch (SQLException | ErroValidacao ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    /**
     * @param sql
     * @param obj
     */
    @Override
    protected void preencheConsulta(PreparedStatement sql, Cliente obj) {
        try {
            sql.setString(1, obj.getNome());
            sql.setString(2, obj.getCpf());
            sql.setString(3, obj.getRg());
            sql.setString(4, obj.getEndereco());
            sql.setString(5, obj.getTelefone());
            if (obj.getCodigo() > 0) {
                sql.setInt(6, obj.getCodigo());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("psc_persistencia.ClienteDAO.preencheConsulta()");
        }
    }

    /**
     * @param codigo
     * @return
     */
    @Override
    public Cliente Abrir(int codigo) {
        try {
            PreparedStatement sql = conn.prepareStatement("select codigo,nome,cpf,rg,endereco,telefone "
                    + "from cliente where codigo = ?");

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
    protected void preencheFiltros(Cliente filtro) {
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
        if (filtro.getEndereco() != null) {
            adicionarFiltro("endereco", "=");
        }
        if (filtro.getTelefone() != null) {
            adicionarFiltro("telefone", "=");
        }
    }

    /**
     * @param sql
     * @param filtro
     */
    @Override
    protected void preencheParametros(PreparedStatement sql, Cliente filtro) {
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
            if (filtro.getEndereco() != null) {
                sql.setString(cont, filtro.getEndereco());
                cont++;
            }
            if (filtro.getTelefone() != null) {
                sql.setString(cont, filtro.getTelefone());
                cont++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
