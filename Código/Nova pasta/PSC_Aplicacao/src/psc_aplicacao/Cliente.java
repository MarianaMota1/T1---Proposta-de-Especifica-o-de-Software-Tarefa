
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_aplicacao;

import java.util.Objects;

/**
 *
 * @author Mary
 */
public class Cliente implements Entidade {

    private int codigo;
    private String cpf;
    private String rg;
    private String endereco;
    private String telefone;
    private String nome;

    public Cliente() {
    }

    public Cliente(int codigo, String cpf, String rg, String endereco, String telefone, String nome) {
        this.codigo = codigo;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.telefone = telefone;
        this.nome = nome;
    }

    public Cliente(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

        
        
        
   

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws ErroValidacao {
        if (cpf.length() > 14) {
            throw new ErroValidacao("O atributo cpf deve ter no máximo 14 caracteres!");
        }
        this.cpf = cpf.replace("-", "").replace(".", "");
    }

    public String getRg() {
        return rg;
    }

    /**
     *
     * @param rg
     * @throws ErroValidacao
     */
    public void setRg(String rg) throws ErroValidacao {
        if (rg.length() > 13) {
            throw new ErroValidacao("O Atributo rg deve ter no máximo 13 caracteres");
        }
        this.rg = rg.replace(".", "").replace("-", "");
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ErroValidacao {
        if (nome.length() > 250) {
            throw new ErroValidacao("O atributo nome deve ter no máximo 250 caracteres!");
        }
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.codigo;
        hash = 17 * hash + Objects.hashCode(this.cpf);
        hash = 17 * hash + Objects.hashCode(this.rg);
        hash = 17 * hash + Objects.hashCode(this.endereco);
        hash = 17 * hash + Objects.hashCode(this.telefone);
        hash = 17 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        return Objects.equals(this.nome, other.nome);
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public int getCodigo() {
        return this.codigo;
    }

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
