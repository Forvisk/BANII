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
    public String toString() {
        return nome + ": {codigo: " + codigo + ", endereco: " + endereco + ", cargo: " + cargo + ", hotel: " + (hotel == null ? "Nao definido" : hotel) + ", estado: " + estado + " }";
    }

    public static void verificaDados(String nome, String telefone, String cpf, String endereco, char estado, String cargo) throws Exception {
        if (nome.length() < 3) {
            throw new Exception("Verifique o nome.");
        }
        if (cpf.length() != 11) {
            throw new Exception("Verifique o CPF. Apenas os 11 digitos.");
        }
        if (endereco.length() < 3) {
            throw new Exception("Verifique o endereço.");
        }
        if (cargo.length() < 3) {
            throw new Exception("Verifique o cargo.");
        }
        if (telefone.length() != 12) {
            throw new Exception("Telefone deve ter 12 digitos!");
        }
        if (estado != 'A' && estado != 'L' && estado != 'D') {
            throw new Exception("O estado deve ser A, L ou D (em maiúsculo)");
        }
    }

    public static Funcionario criarFuncionario(String nome, String telefone, String cpf, String endereco, String cargo, char estado) throws Exception {
        verificaDados(nome, telefone, cpf, endereco, estado, cargo);

        int cod = getNewCodigo();

        return new Funcionario(cod, nome, telefone, cpf, endereco, cargo, estado, null);
    }

    // TODO: pegar o prox codigo disponivel
    public static int getNewCodigo() {
        int lastId = 0;
        for (int res : Gerenciador.getInstancia().getFuncionarios().keySet()) {
            if (lastId < res) {
                lastId = res;
            }
        }
        return lastId + 1;
    }

    public String insertQuery() throws Exception {
        verificaDados(nome, telefone, cpf, endereco, estado, cargo);
        return "INSERT INTO funcionario VALUES (" + this.codigo
                + ", \'" + this.nome + "\'"
                + ", \'" + this.telefone + "\'"
                + ", \'" + this.cpf + "\'"
                + ", \'" + this.endereco + "\'"
                + ", \'" + this.cargo + "\'"
                + ", \'" + this.estado + "\');";
    }

    public String updateQuery() throws Exception {
        verificaDados(nome, telefone, cpf, endereco, estado, cargo);
        return "UPDATE funcionario SET "
                + "fun_st_nome = \'" + this.nome + "\'"
                + ", fun_st_telefone = \'" + this.telefone + "\'"
                + ", fun_st_cpf = \'" + this.cpf + "\'"
                + ", fun_st_cargo = \'" + this.cargo + "\'"
                + ", fun_st_estado = \'" + this.estado + "\'"
                + ", fun_st_endereco = \'" + this.endereco + "\' WHERE fun_pk_codigo = " + codigo + ";";
    }

}
