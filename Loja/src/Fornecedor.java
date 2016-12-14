/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mary
 */
public class Fornecedor {
    private String cnpj;
    private String nome;
    private String telefone;
    private String email;

    public Fornecedor(String cnpj, String nome, String telefone, String email) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) throws ErroValidacao {
        if(cnpj.length() > 14)
            throw new ErroValidacao("O atributo cpf deve ter no máximo 18 caracteres!");
        this.cnpj = cnpj.replace("-", "").replace(".", "").replace("/","");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ErroValidacao {
       if(nome.length() > 250)
            throw new ErroValidacao("O atributo nome deve ter no máximo 250 caracteres!");
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
    
    
    
}
