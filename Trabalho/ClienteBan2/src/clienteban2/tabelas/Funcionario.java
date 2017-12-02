/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2.tabelas;

/**
 *
 * @author gustavo
 */
public class Funcionario {

    private int codigo;
    private String nome;
    private String telefone;
    private String cpf;
    private String endereco;
    private String cargo;
    private char estado; // A - Ativo; L - Licansa; D - Demitido/Inativo
    private Hotel hotel;

    public Funcionario(int codigo, String nome, String telefone, String cpf, String endereco, String cargo, char estado, Hotel hotel) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cargo = cargo;
        this.estado = estado;
        this.hotel = hotel;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
    @Override
    public String toString(){
        return nome + ": {codigo: " + codigo + ", endereco: " + endereco + ", cargo: " + cargo + ", hotel: " + (hotel == null ? "Nao definido" : hotel) + ", estado: " + estado + " }";
    }

}
