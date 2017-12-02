/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2.tabelas;

import static clienteban2.tabelas.Hotel.verificaDados;

/**
 *
 * @author gustavo
 */
public class Quarto {

    private int numero;
    private String local;
    private Hotel hotel;
    private TipoQuarto tipo;
    private float preco;
    private Cliente cliente;

    public static Quarto criaQuarto(int codigo, Hotel hotel, TipoQuarto tq, String local, float preco) throws Exception {
        if (hotel == null) {
            throw new Exception("Precisamos de um hotel.");
        }
        if (tq == null) {
            throw new Exception("Precisamos de um tipo.");
        }
        if (local.length() != 14) {
            throw new Exception("Verifique o local.");
        }
        if (preco < 0) {
            throw new Exception("Preco não pode ser negativo!");
        }
        return new Quarto(codigo, local, hotel, tq, preco);
    }

    public Quarto(int numero, String local, Hotel hotel, TipoQuarto tipo, float preco) {
        this.numero = numero;
        this.local = local;
        this.hotel = hotel;
        this.tipo = tipo;
        this.preco = preco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Quarto: {numero: " + numero + ", local: " + local + ", tipo: " + tipo + ", hotel: " + hotel + ", preco: " + preco + " }";
    }

    public String isVago() {
        return this.cliente != null ? "Sim" : "Não";
    }

    public String insertQuery() throws Exception {
        return "INSERT INTO quarto VALUES (" + this.numero
                + ", \'" + this.local + "\'"
                + ", " + this.tipo.getCodigo() + ""
                + ", " + this.hotel.getCodigo() + ");";
    }
    
    public String deleteQuery() throws Exception {
        return "DELETE FROM quarto WHERE qua_pk_numero = " + this.numero + ";";
    }

}
