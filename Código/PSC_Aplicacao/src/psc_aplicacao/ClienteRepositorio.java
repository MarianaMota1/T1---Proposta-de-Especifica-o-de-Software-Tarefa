/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_aplicacao;

/**
 *
 * @author Mary
 */
public interface ClienteRepositorio extends Repositorio<Cliente> {

    public Cliente Abrir(String cpf);

    public Cliente Buscar(String filtro);

}
