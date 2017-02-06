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
import psc_aplicacao.ProdutoRepositorio;
import psc_aplicacao.Produto;

/**
 *
 * @author Mary
 */
public class ProdutoDAO extends DAOGenerico<Produto> implements ProdutoRepositorio {

    public ProdutoDAO() {
        setConsultaAbrir("select id, nome, preco from produtos where id = ?");
        setConsultaApagar("delete from produtos where id = ?");
        setConsultaInserir("insert into produtos(nome,preco) values(?,?)");
        setConsultaAlterar("update produtos set nome = ?, preco = ? where id = ?");
        setConsultaBusca("select id, nome, preco from produtos ");
        setConsultaUltimoCodigo("select max(codigo) from produtos where nome = ? and preco = ?");
    }

    /**
     *
     * @param resultado
     * @return
     */
    @Override
    protected Produto preencheObjeto(ResultSet resultado) {
        try {
            Produto tmp = new Produto();
            tmp.setCodigo(resultado.getInt(1));
            try {
                tmp.setNome(resultado.getString(2));
            } catch (ErroValidacao ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            tmp.setPrecoUnitario(resultado.getBigDecimal(3));
            return tmp;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param sql
     * @param obj
     */
    @Override
    protected void preencheConsulta(PreparedStatement sql, Produto obj) {
        try {
            sql.setString(1, obj.getNome());
            sql.setBigDecimal(2, obj.getPrecoUnitario());
            if (obj.getCodigo() > 0) {
                sql.setInt(3, obj.getCodigo());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param filtro
     */
    @Override
    protected void preencheFiltros(Produto filtro) {
        if (filtro.getCodigo() > 0) {
            adicionarFiltro("Codigo", "=");
        }
        if (filtro.getNome() != null) {
            adicionarFiltro("nome", " like ");
        }
        if (filtro.getPrecoUnitario() != null) {
            adicionarFiltro("preco", " = ");
        }
    }

    /**
     *
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
                sql.setString(cont, filtro.getNome());
                cont++;
            }
            if (filtro.getPrecoUnitario() != null) {
                sql.setBigDecimal(cont, filtro.getPrecoUnitario());
                cont++;
            }
        } catch (Exception ex) {
        }
    }

}
