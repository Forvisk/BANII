/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2;

/**
 *
 * @author gustavo
 */
public class Coluna {

    private String nome;
    private String tipo;
    private boolean isPK;

    public Coluna(String nome, String tipo, boolean isPK) {
        this.nome = nome;
        this.tipo = tipo;
        this.isPK = isPK;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public boolean isValueValid(String value) {
        if (this.tipo.equals("integer")) {

            return isNumeric(value);

        }
        return this.tipo.equals("string") && !value.isEmpty();
    }

    public boolean isValueValid(int value) {
        return this.tipo.equals("integer");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isPrimaryKey() {
        return this.isPK;
    }

    public void setPrimaryKey(boolean pk) {
        this.isPK = pk;
    }

    @Override
    public String toString() {
        return "Coluna{" + "nome=" + nome + ", tipo=" + tipo + (isPK ? ", Primary Key}" : '}');
    }
}
