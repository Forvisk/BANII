/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2.tabelas;

import java.util.HashMap;

/**
 *
 * @author gustavo
 */
public class TipoQuarto {

    private int codigo;
    private String nome;

    private final HashMap<Hotel, Float> precos;

    public TipoQuarto(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.precos = new HashMap<>();
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

    public HashMap<Hotel, Float> getPrecos() {
        return precos;
    }

    @Override
    public String toString() {
        return "Tipo de Quarto: {Codigo: " + codigo + ", nome: " + nome + "}";
    }

    public String setPreco(Hotel hotel, float preco) {
        if (precos.containsKey(hotel)) {
            precos.put(hotel, preco);
            return "UPDATE Quarto_Preco SET qpr_in_diaria = " + preco + " WHERE tqu_pfk_codigo = " + codigo + " AND hot_pfk_codigo = " + hotel.getCodigo() + ";"; 
        }

        return "INSERT INTO Quarto_Preco VALUES(" + codigo + ", " + hotel.getCodigo() + ", " + preco + ");";

    }

}
