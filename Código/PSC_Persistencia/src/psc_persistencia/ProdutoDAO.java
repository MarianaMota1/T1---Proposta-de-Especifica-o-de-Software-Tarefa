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
import psc_aplicacao.Funcionario;
import psc_aplicacao.FuncionarioRepositorio;
import psc_aplicacao.Produto;
import psc_aplicacao.ProdutoRepositorio;

/**
 *
 * @author Mary
 */
public class ProdutoDAO extends DAOGenerico<Produto> implements ProdutoRepositorio {

    public ProdutoDAO() {
        setConsultaAbrir("select codigo,nome,descricao,precounitario from produto where codigo = ?");
        setConsultaApagar("DELETE FROM produto WHERE codigo = ?");
        setConsultaInserir("INSERT INTO produto(nome,descricao,precounitario) VALUES(?,?,?)");
        setConsultaAlterar("UPDATE produto SET nome = ?, "
                + "descricao = ?, precounitario = ? WHERE codigo = ?");
        setConsultaBusca("select codigo,nome,descricao,precounitario from produto ");
        setConsultaUltimoCodigo("select max(codigo) from produto where nome = ?");
    }

    /**
     *
     * @param resultado
     * @return
     */
    @Override
    protected Produto preencheObjeto(ResultSet resultado) {
        Produto tmp = new Produto();
        try {
            tmp.setCodigo(resultado.getInt(1));
            tmp.setNome(resultado.getString(2));
            tmp.setDescricao(resultado.getString(3));
            tmp.setPrecoUnitario(resultado.getBigDecimal(4));
        } catch (SQLException | ErroValidacao ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    /**
     * @param sql
     * @param obj
     */
    @Override
    protected void preencheConsulta(PreparedStatement sql, Produto obj) {
        try {
            sql.setString(1, obj.getNome());
            sql.setString(2, obj.getDescricao());
            sql.setBigDecimal(3, obj.getPrecoUnitario());
            if (obj.getCodigo() > 0) {
                sql.setInt(4, obj.getCodigo());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param codigo
     * @return
     */
    @Override
    public Produto Abrir(int codigo) {
        try {
            PreparedStatement sql = conn.prepareStatement("select codigo,nome,descricao,precounitario "
                    + "from produto where codigo = ?");

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
    protected void preencheFiltros(Produto filtro) {
        if (filtro.getCodigo() > 0) {
            adicionarFiltro("codigo", "=");
        }
        if (filtro.getNome() != null) {
            adicionarFiltro("nome", " like ");
        }
    }

    /**
     * @param sql
     * @param filtro
     */
    @Override
    protected void preencheParametros(PreparedStatement sql, Produto filtro) {
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
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
