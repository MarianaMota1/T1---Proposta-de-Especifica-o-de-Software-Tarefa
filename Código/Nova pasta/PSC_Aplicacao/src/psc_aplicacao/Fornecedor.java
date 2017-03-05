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
public class Fornecedor implements Entidade {

    private int codigo;
    private String cnpj;
    private String nome;
    private String telefone;
    private String email;

    public Fornecedor() {
    }

    public Fornecedor(int codigo, String cnpj, String nome, String telefone, String email) {
        this.codigo = codigo;
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) throws ErroValidacao {
        if (cnpj.length() > 14) {
            throw new ErroValidacao("O atributo cpf deve ter no máximo 18 caracteres!");
        }
        this.cnpj = cnpj.replace("-", "").replace(".", "").replace("/", "");
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.cnpj);
        hash = 31 * hash + Objects.hashCode(this.nome);
        hash = 31 * hash + Objects.hashCode(this.telefone);
        hash = 31 * hash + Objects.hashCode(this.email);
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
        final Fornecedor other = (Fornecedor) obj;
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
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
