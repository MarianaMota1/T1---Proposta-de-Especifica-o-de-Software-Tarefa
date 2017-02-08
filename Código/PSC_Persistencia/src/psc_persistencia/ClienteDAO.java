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
public class ClienteDAO extends DAOGenerico<Cliente> implements ClienteRepositorio{
    
    public ClienteDAO() {
        setConsultaAbrir("select codigo,cpf,rg,endereco, telefone, nome from clientes where codigo = ?");
        setConsultaApagar("DELETE FROM clientes WHERE codgio = ? ");
        setConsultaInserir("INSERT INTO clientes(nome,cpf) VALUES(?,?,?)");
        setConsultaAlterar("UPDATE clientes SET nome = ?, "
                        + "cpf = ?,cpf = ?,rg = ?,endereco= ? ,telefone = ?"
                        + "WHERE codigo = ?");
        setConsultaBusca("select codigo,cpf,rg,endereco,telefone,nome from cliente");
        setConsultaUltimoCodigo("select max(codigo) from clientes where nome = ? and cpf = ?");
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
         } catch (SQLException | ErroValidacao ex) {     
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                return tmp;
    }
    
    /**
     *
     * @param sql
     * @param obj
     */
    @Override
    protected void preencheConsulta(PreparedStatement sql, Cliente obj) {
        try {
            sql.setString(1, obj.getNome());
            sql.setString(2, obj.getCpf());
            
            if(obj.getCodigo() > 0) sql.setInt(4,obj.getCodigo());
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param cpf
     * @return
     */
    @Override
    public Cliente Abrir(String cpf) {
        try {
            
            PreparedStatement sql = conn.prepareStatement("select codigo,cpf,rg,endereco, telefone, nome "
                    + "from clientes where cpf = ?");
            
            sql.setString(1, cpf);
            
            ResultSet resultado = sql.executeQuery();
            
            if(resultado.next()){
                
                return preencheObjeto(resultado);
            }            
        }  catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }
    
    /**
     *
     * @param filtro
     */
    @Override
    protected void preencheFiltros(Cliente filtro) {
        
        if(filtro.getCodigo() > 0) adicionarFiltro("codigo", "=");
        if(filtro.getNome() != null) adicionarFiltro("nome", " like ");
        if(filtro.getCpf() != null) adicionarFiltro("cpf", "=");
    }
    
    /**
     *
     * @param sql
     * @param filtro
     */
    @Override
     protected void preencheParametros(PreparedStatement sql, Cliente filtro) {
        try {
            int cont = 1;
            if(filtro.getCodigo() > 0){ sql.setInt(cont, filtro.getCodigo()); cont++; }
            if(filtro.getNome() != null ){ sql.setString(cont, filtro.getNome() +"%"); cont++; }
            if(filtro.getCpf() != null){ 
                sql.setString(cont, filtro.getCpf()); 
                cont++; 
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
    
    