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
import psc_aplicacao.Produto;
import psc_aplicacao.ProdutoRepositorio;

/**
 *
 * @author Mary
 */
public class ProdutoDAO extends DAOGenerico<Produto> implements ProdutoRepositorio {

    public ProdutoDAO() {
        setConsultaAbrir("select codigo,nome,qtd,descricao,precounitario from produto where codigo = ?");
        setConsultaApagar("DELETE FROM produto WHERE codigo = ?");
        setConsultaInserir("INSERT INTO produto(nome,qtd,descricao,precounitario) VALUES(?,?,?,?)");
        setConsultaAlterar("UPDATE produto SET nome = ?, qtd = ?, "
                + "descricao = ?, precounitario = ? WHERE codigo = ?");
        setConsultaBusca("select codigo,nome,qtd,descricao,precounitario from produto ");
        setConsultaUltimoCodigo("select max(codigo) from produto where nome = ? and qtd = ? and descricao = ? and precounitario = ?");
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
            tmp.setQtd(resultado.getInt(3));
            tmp.setDescricao(resultado.getString(4));
            tmp.setPrecoUnitario(resultado.getBigDecimal(5));
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
            sql.setInt(2, obj.getQtd());
            sql.setString(3, obj.getDescricao());
            sql.setBigDecimal(4, obj.getPrecoUnitario());
            if (obj.getCodigo() > 0) {
                sql.setInt(5, obj.getCodigo());
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
            PreparedStatement sql = conn.prepareStatement("select codigo,nome,qtd,descricao,precounitario "
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
        if (filtro.getQtd() > 0) {
            adicionarFiltro("qtd", "=");
        }
        if (filtro.getDescricao() != null) {
            adicionarFiltro("descricao", "=");
        }
        if (filtro.getPrecoUnitario() != null) {
            adicionarFiltro("precounitario", "=");
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
            if (filtro.getQtd() > 0) {
                sql.setInt(cont, filtro.getQtd());
                cont++;
            }
            if (filtro.getDescricao() != null) {
                sql.setString(cont, filtro.getDescricao());
                cont++;
            }
            if (filtro.getPrecoUnitario() != null) {
                sql.setBigDecimal(cont, filtro.getPrecoUnitario());
                cont++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
