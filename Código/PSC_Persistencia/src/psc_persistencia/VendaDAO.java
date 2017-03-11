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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import psc_aplicacao.Cliente;
import psc_aplicacao.ClienteRepositorio;
import psc_aplicacao.ErroValidacao;
import psc_aplicacao.FormaPagamento;
import psc_aplicacao.Fornecedor;
import psc_aplicacao.FornecedorRepositorio;
import psc_aplicacao.MeioPagamento;
import psc_aplicacao.Venda;
import psc_aplicacao.VendaItem;
import psc_aplicacao.VendaRepositorio;

/**
 *
 * @author Mary
 */
public class VendaDAO extends DAOGenerico<Venda> implements VendaRepositorio {

    private ClienteDAO clienteDAO;
    private ProdutoDAO produtoDAO;

    public VendaDAO() {
        setConsultaAbrir("select codigo,cliente,data,valortotal from venda where codigo = ?");
        setConsultaApagar("DELETE FROM venda WHERE codigo = ? ");
        setConsultaInserir("INSERT INTO venda(cliente,data,valortotal) VALUES(?,?,?)");
        setConsultaAlterar("UPDATE venda SET cliente = ?,"
                + "data = ?, valortotal = ? WHERE codigo = ?");
        setConsultaBusca("select codigo,cliente,data,valortotal from venda ");
        setConsultaUltimoCodigo("select max(codigo) from venda where cliente = ? and data = ? and valortotal = ?");
        clienteDAO = new ClienteDAO();
        produtoDAO = new ProdutoDAO();
    }

    @Override
    public boolean Apagar(Venda obj) {
        try {
            PreparedStatement sql = conn.prepareStatement("delete from vendaitem where venda = ?");
            sql.setInt(1, obj.getCodigo());
            sql.executeUpdate();
            return super.Apagar(obj);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
            tmp.setFormaPagamento(carregaFormaPagamento(tmp));
            tmp.setMeioPagamento(carregaMeioPagamento(tmp));
            tmp.setItens(carregaProdutos(tmp));
        } catch (SQLException ex) {
            System.out.println("psc_persistencia.VendaDAO.preencheObjeto()");
        }
        return tmp;
    }

    private MeioPagamento carregaMeioPagamento(Venda obj) {
        MeioPagamento item = new MeioPagamento(0, null);
        String consulta = "select codigo,meiopagamento,venda from meiopagamento where venda = ?";
        try {
            PreparedStatement sql = conn.prepareStatement(consulta);
            sql.setInt(1, obj.getCodigo());
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                item.setCodigo(resultado.getInt(1));
                item.setMeioPagamento(resultado.getString(2));
                item.setVenda(obj);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return item;
    }

    private FormaPagamento carregaFormaPagamento(Venda obj) {
        FormaPagamento item = new FormaPagamento(0, null);
        String consulta = "select codigo,tipopagamento,venda from formapagamento where venda = ?";
        try {
            PreparedStatement sql = conn.prepareStatement(consulta);
            sql.setInt(1, obj.getCodigo());
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                item.setCodigo(resultado.getInt(1));
                item.setTipoPagamento(resultado.getString(2));
                item.setVenda(obj);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return item;
    }

    private List<VendaItem> carregaProdutos(Venda obj) {
        List<VendaItem> ret = new ArrayList<>();
        String consulta = "select codigo,venda,produto,quantidade from vendaitem where venda = ?";
        try {
            PreparedStatement sql = conn.prepareStatement(consulta);
            sql.setInt(1, obj.getCodigo());
            ResultSet resultado = sql.executeQuery();
            while (resultado.next()) {
                VendaItem item = new VendaItem();
                item.setCodigo(resultado.getInt(1));
                item.setVenda(obj);
                item.setProduto(produtoDAO.Abrir(resultado.getInt(3)));
                item.setQuantidade(resultado.getInt(4));
                ret.add(item);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ret;
    }

    @Override
    public boolean Salvar(Venda obj) {
        if (!super.Salvar(obj)) {
            return false;
        }
        if (obj.getCodigo() > 0) {
            try {

                if (obj.getFormaPagamento().getCodigo() > 0) {
                    String consulta1 = "insert into formapagamento(tipopagamento,venda) values(?,?)";
                    PreparedStatement sql1 = conn.prepareStatement(consulta1);
                    sql1.setString(1, obj.getFormaPagamento().getTipoPagamento());
                    sql1.setInt(2, obj.getCodigo());
                    sql1.executeUpdate();
                } else {
                    String consulta1 = "update formapagamento set tipopagamento =? where venda = ?";
                    PreparedStatement sql1 = conn.prepareStatement(consulta1);
                    sql1.setString(1, obj.getFormaPagamento().getTipoPagamento());
                    sql1.setInt(2, obj.getCodigo());
                    sql1.executeUpdate();
                }

                if (obj.getFormaPagamento().getCodigo() > 0) {
                    String consulta2 = "insert into meiopagamento(meiopagamento,venda) values(?,?)";
                    PreparedStatement sql2 = conn.prepareStatement(consulta2);
                    sql2.setString(1, obj.getMeioPagamento().getMeioPagamento());
                    sql2.setInt(2, obj.getCodigo());
                    sql2.executeUpdate();
                } else {
                    String consulta2 = "update meiopagamento set meiopagamento = ? where venda = ?";
                    PreparedStatement sql2 = conn.prepareStatement(consulta2);
                    sql2.setString(1, obj.getMeioPagamento().getMeioPagamento());
                    sql2.setInt(2, obj.getCodigo());
                    sql2.executeUpdate();
                }

                for (VendaItem item : obj.getItens()) {
                    if (item.getCodigo() == 0) {
                        String consulta3 = "update produto set qtd = ? where codigo = ?";
                        PreparedStatement sql3 = conn.prepareStatement(consulta3);
                        sql3.setInt(1, item.getProduto().getQtd() - item.getQuantidade());
                        sql3.setInt(2, item.getProduto().getCodigo());
                        sql3.executeUpdate();

                        String consulta = "insert into vendaitem(venda, produto,quantidade) values(?,?,?)";
                        PreparedStatement sql = conn.prepareStatement(consulta);
                        sql.setInt(1, obj.getCodigo());
                        sql.setInt(2, item.getProduto().getCodigo());
                        sql.setInt(3, item.getQuantidade());
                        sql.executeUpdate();
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
                            Logger.getLogger(VendaDAO.class
                                    .getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(VendaDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
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
                    + "from venda where codigo = ?");

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
        if (filtro.getData() != null) {
            adicionarFiltro("data", " = ");
        }
        if (filtro.getValorTotal() != null) {
            adicionarFiltro("valortotal", " = ");
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
            if (filtro.getData() != null) {
                sql.setDate(cont, filtro.getData());
                cont++;
            }
            if (filtro.getValorTotal() != null) {
                sql.setBigDecimal(cont, filtro.getValorTotal());
                cont++;
            }
        } catch (SQLException ex) {
            System.out.println("psc_persistencia.VendaDAO.preencheParametros()");
        }
    }

}
