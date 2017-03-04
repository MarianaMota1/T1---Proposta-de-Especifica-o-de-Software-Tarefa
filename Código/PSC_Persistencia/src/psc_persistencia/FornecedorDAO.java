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
import psc_aplicacao.Fornecedor;
import psc_aplicacao.FornecedorRepositorio;

/**
 *
 * @author Mary
 */
public class FornecedorDAO extends DAOGenerico<Fornecedor> implements FornecedorRepositorio {

    public FornecedorDAO() {
        setConsultaAbrir("select codigo,nome,cnpj,telefone,email from fornecedor where codigo = ?");
        setConsultaApagar("DELETE FROM fornecedor WHERE codigo = ? ");
        setConsultaInserir("INSERT INTO fornecedor(nome,cnpj,telefone,email) VALUES(?,?,?,?)");
        setConsultaAlterar("UPDATE fornecedor SET nome = ?, cnpj = ?, "
                + "telefone = ?, email= ? WHERE codigo = ?");
        setConsultaBusca("select codigo,nome,cnpj,telefone,email from fornecedor ");
        setConsultaUltimoCodigo("select max(codigo) from fornecedor where nome = ? and cnpj = ? and "
                + "telefone = ? and email = ?");
    }

    /**
     *
     * @param resultado
     * @return
     */
    @Override
    protected Fornecedor preencheObjeto(ResultSet resultado) {
        Fornecedor tmp = new Fornecedor();
        try {
            tmp.setCodigo(resultado.getInt(1));
            tmp.setNome(resultado.getString(2));
            tmp.setCnpj(resultado.getString(3));
            tmp.setTelefone(resultado.getString(4));
            tmp.setEmail(resultado.getString(5));
        } catch (SQLException | ErroValidacao ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    /**
     * @param sql
     * @param obj
     */
    @Override
    protected void preencheConsulta(PreparedStatement sql, Fornecedor obj) {
        try {
            sql.setString(1, obj.getNome());
            sql.setString(2, obj.getCnpj());
            sql.setString(3, obj.getTelefone());
            sql.setString(4, obj.getEmail());
            if (obj.getCodigo() > 0) {
                sql.setInt(5, obj.getCodigo());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param codigo
     * @return
     */
    @Override
    public Fornecedor Abrir(int codigo) {
        try {
            PreparedStatement sql = conn.prepareStatement("select codigo,nome,cnpj,telefone,email "
                    + "from fornecedor where codigo = ?");

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
    protected void preencheFiltros(Fornecedor filtro) {
        if (filtro.getCodigo() > 0) {
            adicionarFiltro("codigo", "=");
        }
        if (filtro.getNome() != null) {
            adicionarFiltro("nome", " like ");
        }
        if (filtro.getCnpj() != null) {
            adicionarFiltro("cnpj", "=");
        }
        if (filtro.getTelefone() != null) {
            adicionarFiltro("telefone", "=");
        }
        if (filtro.getEmail() != null) {
            adicionarFiltro("email", "=");
        }
    }

    /**
     * @param sql
     * @param filtro
     */
    @Override
    protected void preencheParametros(PreparedStatement sql, Fornecedor filtro) {
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
            if (filtro.getCnpj() != null) {
                sql.setString(cont, filtro.getCnpj());
                cont++;
            }
            if (filtro.getTelefone() != null) {
                sql.setString(cont, filtro.getTelefone());
                cont++;
            }
            if (filtro.getEmail() != null) {
                sql.setString(cont, filtro.getEmail());
                cont++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
