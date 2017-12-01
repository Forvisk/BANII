/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2.tabelas;

import clienteban2.Gerenciador;

/**
 *
 * @author gustavo
 */
public class Hotel {
    
    private int codigo;
    private String nome;
    private String cnpj;
    private String cidade;
    private String UF;
    private String endereco;
    private int estrelas;
    
    public static Hotel criarHotel(String nome, String cnpj, String cidade, String UF, String endereco, int estrelas) throws Exception {
        if (nome.length() < 3) throw new Exception("Verifique o nome.");
        if (cnpj.length() != 14) throw new Exception("Verifique o CNPJ. Apenas caracteres.");
        if (cidade.length() < 3) throw new Exception("Verifique a cidade.");
        if (UF.length() != 2) throw new Exception("Verifique a sigla do estado.");
        if (endereco.length() < 3) throw new Exception("Verifique o endereço.");
        if (estrelas < 0 || estrelas > 5) throw new Exception("De 0 a 5 estrelas.");
        
        int cod = getNewCodigo();
        
        return new Hotel(cod, nome, cnpj, cidade, UF, endereco, estrelas);
    }
    
    public static Hotel criarHotel(int codigo, String nome, String cnpj, String cidade, String UF, String endereco, int estrelas) throws Exception {
        return new Hotel(codigo, nome, cnpj, cidade, UF, endereco, estrelas);
    }

    private Hotel(int codigo, String nome, String cnpj, String cidade, String UF, String endereco, int estrelas) {
        this.codigo = codigo;
        this.nome = nome;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.UF = UF;
        this.endereco = endereco;
        this.estrelas = estrelas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    // TODO: pegar o prox codigo disponivel
    private static int getNewCodigo() {
       return Gerenciador.getInstancia().getHoteis().size();
    }
    
    @Override
    public String toString(){
        return "Hotel: " + this.nome + ", " + this.estrelas + " estrelas, em " + this.endereco + ", " + this.cidade + " em " + this.UF;
    }
    
}
