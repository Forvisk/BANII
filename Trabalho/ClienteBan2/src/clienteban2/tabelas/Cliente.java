/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2.tabelas;

import clienteban2.Gerenciador;
import static clienteban2.tabelas.Hotel.verificaDados;

/**
 *
 * @author gustavo
 */
public class Cliente {

    private int codigo;
    private String nome;
    private String telefone;
    private String cpf;
    private String endereco;

    public Cliente(int codigo, String nome, String telefone, String cpf, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public static Cliente criarCliente(String nome, String telefone, String cpf, String endereco) throws Exception {
        verificaDados(nome, telefone, cpf, endereco);

        int cod = getNewCodigo();

        return new Cliente(cod, nome, telefone, cpf, endereco);
    }

    public static void verificaDados(String nome, String telefone, String cpf, String endereco) throws Exception {
        if (nome.length() < 3) {
            throw new Exception("Verifique o nome.");
        }
        if (cpf.length() != 11) {
            throw new Exception("Verifique o CPF. Apenas os 11 digitos.");
        }
        if (endereco.length() < 3) {
            throw new Exception("Verifique o endereço.");
        }
        if (telefone.length() != 12) {
            throw new Exception("Telefone deve ter 12 digitos!");
        }
    }

    // TODO: pegar o prox codigo disponivel
    private static int getNewCodigo() {
        return Gerenciador.getInstancia().getClientes().size() + 1;
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

    @Override
    public String toString() {
        return nome + " (" + codigo + "): {cpf: " + cpf + ", telefone: " + telefone + "+ " + endereco + "}";
    }

    public String insertQuery() throws Exception {
        verificaDados(nome, telefone, cpf, endereco);
        return "INSERT INTO cliente VALUES (" + this.codigo
                + ", \'" + this.nome + "\'"
                + ", \'" + this.telefone + "\'"
                + ", \'" + this.cpf + "\'"
                + ", \'" + this.endereco + "\');";
    }

    public String updateQuery() throws Exception {
        verificaDados(nome, telefone, cpf, endereco);
        return "UPDATE cliente SET "
                + "cli_st_nome = \'" + this.nome + "\'"
                + ", cli_st_telefone = \'" + this.telefone + "\'"
                + ", cli_st_cpf = \'" + this.cpf + "\'"
                + ", cli_st_endereco = \'" + this.endereco + "\' WHERE cli_pk_codigo = " + codigo + ";";
    }

}
