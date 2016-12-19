
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mary
 */
public class Cliente {
    private String cpf;
    private String rg;
    private String endereco;
    private String telefone;
    private String nome;

    public Cliente(String cpf, String rg, String endereco, String telefone, String nome) {
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.telefone = telefone;
        this.nome = nome;
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

    /**
     *
     * @param rg
     */
    public void setRg(String rg) throws ErroValidacao  {
        if(rg.length() > 12 )
            throw new ErroValidacao("O Atributo rg deve ter no máximo 12 caracteres");
        this.cpf = cpf.replace(".","").replace("-","");
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
        if(nome.length() > 250)
            throw new ErroValidacao("O atributo nome deve ter no máximo 250 caracteres!");
        this.nome = nome;
    }



}

   