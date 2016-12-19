/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mary
 */
public class Funcionario {
    private String cpf;
    private String rg;
    private String nome;
    private String funcao;

    public Funcionario(String cpf, String rg, String nome, String funcao) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.funcao = funcao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws ErroValidacao {
        if(cpf.length() > 14)
            throw new ErroValidacao("O atributo cpf deve ter no máximo 14 caracteres!");
        this.cpf = cpf.replace("-", "").replace(".", "");
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) throws ErroValidacao {
        if(rg.length() > 12 )
            throw new ErroValidacao("O Atributo rg deve ter no máximo 12 caracteres");
        this.cpf = cpf.replace(".","").replace("-","");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ErroValidacao {
         if(nome.length() > 250)
            throw new ErroValidacao("O atributo nome deve ter no máximo 250 caracteres!");
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) throws ErroValidacao {
        if(funcao.length() > 250)
            throw new ErroValidacao("O atributo função deve ter no máximo 250 caracteres!");
        this.funcao = funcao;
    }
    
    
          
    
    
    
}
