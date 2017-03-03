/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_persistencia;

import psc_aplicacao.Entidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import psc_aplicacao.Repositorio;

/**
 *
 * @author Mary
 * @param <T>
 */
public abstract class DAOGenerico<T extends Entidade> implements Repositorio<T> {

    protected Connection conn;

    private String consultaAbrir;
    private String consultaApagar;
    private String consultaInserir;
    private String consultaAlterar;
    private String consultaBusca;
    private String consultaUltimoCodigo;

    String where = "";

    public DAOGenerico() {
        try {
            DB.Iniciar();
            conn = DB.criarConexao();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado!");
        } catch (SQLException ex) {
            System.out.println("Usuário/senha errados!");
        } catch (Exception ex) {
            Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract T preencheObjeto(ResultSet resultado);

    protected abstract void preencheConsulta(PreparedStatement sql, T obj);

    protected abstract void preencheFiltros(T filtro);

    protected abstract void preencheParametros(PreparedStatement sql, T filtro);

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean Salvar(T obj) {
        try {
            if (obj.getCodigo() == 0) {
                
                System.out.println(getConsultaInserir());

                PreparedStatement sql = conn.prepareStatement(getConsultaInserir());

                preencheConsulta(sql, obj);

                sql.executeUpdate();

//                PreparedStatement sql2 = conn.prepareStatement(getConsultaUltimoCodigo());
//
//                preencheConsulta(sql2, obj);
//
//                ResultSet resultado = sql2.executeQuery();
//
//                if (resultado.next()) {
//
//                    obj.setCodigo(resultado.getInt(1));
//                }

            } else {

                PreparedStatement sql = conn.prepareStatement(getConsultaAlterar());

                preencheConsulta(sql, obj);

                sql.executeUpdate();

            }
            return true;

        } catch (SQLException e) {
            Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean Apagar(T obj) {
        try {
            if (obj.getCodigo() > 0) {
                // Objeto não está no BD, inserir

                // Cria a consulta sql
                PreparedStatement sql = conn.prepareStatement(getConsultaApagar());

                // Passa os parâmetros para a consulta SQL
                sql.setInt(1, obj.getCodigo());

                // Executa a consulta SQL
                sql.executeUpdate();

            }

            return true;

        } catch (SQLException e) {
            Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    /**
     *
     * @param codigo
     * @return
     */
    @Override
    public T Abrir(int codigo) {
        try {

            PreparedStatement sql = conn.prepareStatement(getConsultaAbrir());

            sql.setInt(1, codigo);

            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {

                return preencheObjeto(resultado);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public List<T> Buscar(T filtro) {

        List<T> ret = new ArrayList<>();

        where = "";
        
        if (filtro != null) {
            preencheFiltros(filtro);

            if (where.length() > 0) {
                where = "WHERE " + where;
            }
        }

        try {

            PreparedStatement sql = conn.prepareStatement(getConsultaBusca() + where);

            if (filtro != null) {
                preencheParametros(sql, filtro);
            }

            ResultSet resultado = sql.executeQuery();
            
            while (resultado.next()) {

                T tmp = preencheObjeto(resultado);

                ret.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    protected void adicionarFiltro(String campo, String operador) {
        if (where.length() > 0) {
            where = where + " and ";
        }
        where = where + campo + " " + operador + " ?";
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getConsultaAbrir() {
        return consultaAbrir;
    }

    public void setConsultaAbrir(String consultaAbrir) {
        this.consultaAbrir = consultaAbrir;
    }

    public String getConsultaApagar() {
        return consultaApagar;
    }

    public void setConsultaApagar(String consultaApagar) {
        this.consultaApagar = consultaApagar;
    }

    public String getConsultaInserir() {
        return consultaInserir;
    }

    public void setConsultaInserir(String consultaInserir) {
        this.consultaInserir = consultaInserir;
    }

    public String getConsultaAlterar() {
        return consultaAlterar;
    }

    public void setConsultaAlterar(String consultaAlterar) {
        this.consultaAlterar = consultaAlterar;
    }

    public String getConsultaBusca() {
        return consultaBusca;
    }

    public void setConsultaBusca(String consultaBusca) {
        this.consultaBusca = consultaBusca;
    }

    public String getConsultaUltimoCodigo() {
        return consultaUltimoCodigo;
    }

    public void setConsultaUltimoCodigo(String consultaUltimoCodigo) {
        this.consultaUltimoCodigo = consultaUltimoCodigo;
    }

}
