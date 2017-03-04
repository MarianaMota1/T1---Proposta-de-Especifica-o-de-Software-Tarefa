/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_aplicacao;

import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Mary
 */
public interface FornecedorRepositorio extends Repositorio<Fornecedor> {

    @Override
    public Fornecedor Abrir(int codigo);

}
