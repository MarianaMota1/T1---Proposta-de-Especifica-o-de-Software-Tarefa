/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_persistencia;

import java.sql.Date;
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
import psc_aplicacao.Venda;
import psc_aplicacao.VendaItem;
import psc_aplicacao.VendaRepositorio;

/**
 *
 * @author Mary
 */
public class VendaDAO extends DAOGenerico<Venda> implements VendaRepositorio {

    private ClienteDAO clienteDAO;

    public VendaDAO() {
        setConsultaAbrir("select codigo,cliente,data,valortotal from venda where codigo = ?");
        setConsultaApagar("DELETE FROM venda WHERE codigo = ? ");
        setConsultaInserir("INSERT INTO venda(cliente,data,valortotal) VALUES(?,?,?)");
        setConsultaAlterar("UPDATE venda SET cliente = ?,"
                + "data = ?, valortotal = ? WHERE codigo = ?");
        setConsultaBusca("select codigo,cliente,data,valortotal from venda ");
        setConsultaUltimoCodigo("select max(codigo) from venda where cliente = ?");
    }

    /**
     *
     * @param resultado
     * @return
     */
    @Override
    protected Venda preencheObjeto(ResultSet resultado) {
        Venda tmp = new Venda();
        try {
            tmp.setCodigo(resultado.getInt(1));
            tmp.setCliente(clienteDAO.Abrir(resultado.getInt(2)));
            tmp.setData(resultado.getDate(3));
            tmp.setValorTotal(resultado.getBigDecimal(4));
        } catch (SQLException ex) {
            System.out.println("psc_persistencia.VendaDAO.preencheObjeto()");
        }
        return tmp;
    }

    @Override
    public boolean Salvar(Venda obj) {
        if (!super.Salvar(obj)) {
            return false;
        }
        if (obj.getCodigo() > 0) {
            for (VendaItem item : obj.getItens()) {
                if (item.getCodigo() == 0) {
                    try {
                        String consulta = "insert into vendaitem(venda, produto,quantidade) values(?,?,?)";
                        PreparedStatement sql = conn.prepareStatement(consulta);
                        sql.setInt(1, obj.getCodigo());
                        sql.setInt(2, item.getProduto().getCodigo());
                        sql.setInt(3, item.getQuantidade());
                        sql.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                } else {
                    try {
                        String consulta = "update vendaitem set venda = ?, produto = ?,quantidade = ? where codigo = ?";
                        PreparedStatement sql = conn.prepareStatement(consulta);
                        sql.setInt(1, obj.getCodigo());
                        sql.setInt(2, item.getProduto().getCodigo());
                        sql.setInt(3, item.getQuantidade());
                        sql.setInt(4, item.getCodigo());
                        sql.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param sql
     * @param obj
     */
    @Override
    protected void preencheConsulta(PreparedStatement sql, Venda obj) {
        try {
            sql.setInt(1, obj.getCliente().getCodigo());
            sql.setDate(2, obj.getData());
            sql.setBigDecimal(3, obj.getValorTotal());
            if (obj.getCodigo() > 0) {
                sql.setInt(4, obj.getCodigo());
            }
        } catch (SQLException ex) {
            System.out.println("psc_persistencia.VendaDAO.preencheConsulta()");
        }
    }

    /**
     * @param codigo
     * @return
     */
    @Override
    public Venda Abrir(int codigo) {
        try {
            PreparedStatement sql = conn.prepareStatement("select codigo,cliente,data,valortotal "
                    + "from produto where codigo = ?");

            sql.setInt(1, codigo);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                return preencheObjeto(resultado);
            }
        } catch (SQLException ex) {
            System.out.println("psc_persistencia.VendaDAO.Abrir()");
        }
        return null;
    }

    /**
     * @param filtro
     */
    @Override
    protected void preencheFiltros(Venda filtro) {
        if (filtro.getCodigo() > 0) {
            adicionarFiltro("codigo", "=");
        }
        if (filtro.getCliente() != null) {
            adicionarFiltro("cliente", " = ");
        }
    }

    /**
     * @param sql
     * @param filtro
     */
    @Override
    protected void preencheParametros(PreparedStatement sql, Venda filtro) {
        try {
            int cont = 1;
            if (filtro.getCodigo() > 0) {
                sql.setInt(cont, filtro.getCodigo());
                cont++;
            }
            if (filtro.getCliente() != null) {
                sql.setInt(cont, filtro.getCliente().getCodigo());
                cont++;
            }
        } catch (SQLException ex) {
            System.out.println("psc_persistencia.VendaDAO.preencheParametros()");
        }
    }

}
