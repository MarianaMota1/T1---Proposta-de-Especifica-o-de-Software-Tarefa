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
public class Funcionario implements Entidade {

    private int codigo;
    private String cpf;
    private String rg;
    private String nome;
    private String funcao;
    private String senha;

    public Funcionario() {
    }

    public Funcionario(int codigo, String cpf, String rg, String nome, String funcao, String senha) {
        this.codigo = codigo;
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.funcao = funcao;
        this.senha = senha;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public void setRg(String rg) throws ErroValidacao {
        if (rg.length() > 13) {
            throw new ErroValidacao("O Atributo rg deve ter no máximo 12 caracteres");
        }
        this.rg = rg.replace(".", "").replace("-", "");
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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) throws ErroValidacao {
        if (funcao.length() > 250) {
            throw new ErroValidacao("O atributo função deve ter no máximo 250 caracteres!");
        }
        this.funcao = funcao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.cpf);
        hash = 97 * hash + Objects.hashCode(this.rg);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.funcao);
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return Objects.equals(this.funcao, other.funcao);
    }

    @Override
    public String toString() {
        return "Funcionario{" + "cpf=" + cpf + ", rg=" + rg + ", nome=" + nome + ", funcao=" + funcao + '}';
    }

}
