
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_aplicacao;

import java.util.List;

/**
 *
 * @author Mary
 * @param <T>
 */
public interface Repositorio<T extends Entidade>{
    public boolean Salvar(T obj);
    public boolean Apagar(T obj);
    public T Abrir(int codigo);
    public List<T> Buscar(T filtro);
    
}
