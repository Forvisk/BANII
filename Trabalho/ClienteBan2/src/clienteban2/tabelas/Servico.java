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
public class Servico {

    private int codigo;
    private char tipo;
    private String descricao;
    private float preco;
    
    public static Servico criarServico(char charAt, String text, Float valueOf) throws Exception {
        verificaDados(charAt, text, valueOf);
        return new Servico(getNextId(), charAt, text, valueOf);
    }
    
    public static int getNextId(){
        int lastId = 0;
        for (int res : Gerenciador.getInstancia().getServicos().keySet()){
            if (lastId < res){
                lastId = res;
            }
        }
        return lastId + 1;
    }

    public Servico(int codigo, char tipo, String descricao, float preco) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    private static void verificaDados(char c, String s, float f) throws Exception{
        if (s.length() < 3){
            throw new Exception("Verifique a descricao!");
        }
    }

    public String insertQuery() throws Exception {
        verificaDados(tipo, descricao, preco);
        return "INSERT INTO servico VALUES (" + this.codigo
                + ", \'" + this.tipo + "\'"
                + ", \'" + this.descricao + "\'"
                + ", " + this.preco + ");";
    }
    
    
}
