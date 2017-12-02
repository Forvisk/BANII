/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2.tabelas;

import clienteban2.Gerenciador;
import java.util.HashMap;

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
    private final HashMap<Integer, Quarto> quartos;
    private final HashMap<Integer, Funcionario> funcionarios;

    public static Hotel criarHotel(String nome, String cnpj, String cidade, String UF, String endereco, int estrelas) throws Exception {
        verificaDados(nome, cnpj, cidade, UF, endereco, estrelas);

        int cod = getNewCodigo();

        return new Hotel(cod, nome, cnpj, cidade, UF, endereco, estrelas);
    }

    public static void verificaDados(String nome, String cnpj, String cidade, String UF, String endereco, int estrelas) throws Exception {
        if (nome.length() < 3) {
            throw new Exception("Verifique o nome.");
        }
        if (cnpj.length() != 14) {
            throw new Exception("Verifique o CNPJ. Apenas os 14 digitos.");
        }
        if (cidade.length() < 3) {
            throw new Exception("Verifique a cidade.");
        }
        if (UF.length() != 2) {
            throw new Exception("Verifique a sigla do estado.");
        }
        if (endereco.length() < 3) {
            throw new Exception("Verifique o endereço.");
        }
        if (estrelas < 0 || estrelas > 5) {
            throw new Exception("De 0 a 5 estrelas.");
        }
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
        this.quartos = new HashMap<>();
        this.funcionarios = new HashMap<>();
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

    public HashMap<Integer, Quarto> getQuartos() {
        return this.quartos;
    }

    public HashMap<Integer, Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    // TODO: pegar o prox codigo disponivel
    private static int getNewCodigo() {
        return Gerenciador.getInstancia().getHoteis().size() + 1;
    }

    @Override
    public String toString() {
        return "Hotel( " + this.codigo + " ) : {\n\tNome: " + this.nome
                + "\n\t" + this.estrelas + " estrelas"
                + "\n\tEndereço: " + this.endereco + ", " + this.cidade + " em " + this.UF
                + "\n\tCNPJ: " + this.cnpj
                + "\n}";

    }

    public String updateQuery() throws Exception {
        verificaDados(nome, cnpj, cidade, UF, endereco, estrelas);
        return "UPDATE hotel SET "
                + "hot_st_nome = \'" + this.nome + "\', "
                + "hot_st_cnpj = \'" + this.cnpj + "\', "
                + "hot_st_cidade = \'" + this.cidade + "\', "
                + "hot_st_uf = \'" + this.UF + "\', "
                + "hot_st_endereco = \'" + this.endereco + "\', "
                + "hot_in_estrelas = \'" + this.estrelas + "\'"
                + "WHERE hot_pk_codigo = \'" + this.codigo + "\';";

    }

    public String insertQuery() throws Exception {
        verificaDados(nome, cnpj, cidade, UF, endereco, estrelas);
        return "INSERT INTO hotel VALUES (" + this.codigo
                + ", \'" + this.nome + "\'"
                + ", \'" + this.cnpj + "\'"
                + ", \'" + this.cidade + "\'"
                + ", \'" + this.UF + "\'"
                + ", \'" + this.endereco + "\'"
                + ", " + this.estrelas + ");";
    }

    public void addQuarto(Quarto quarto) {
        this.quartos.put(quarto.getNumero(), quarto);
    }

    public void addFuncionario(Funcionario fun) {
        this.funcionarios.put(fun.getCodigo(), fun);
    }

}
