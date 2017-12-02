/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2.tabelas;

import java.sql.Timestamp;

/**
 *
 * @author gustavo
 */
public class ServicoPrestado {
    private int codigo;
    private String timestamp_servico;
    
    private boolean concluido;
    private boolean cortesia;
    
    private Servico servico;
    private Funcionario funcionario;
    private Estadia estadia;
    private Quarto quarto;

    public ServicoPrestado(int codigo, String timestamp_servico, boolean concluido, boolean cortesia, Servico servico, Funcionario funcionario, Estadia estadia, Quarto quarto) {
        this.codigo = codigo;
        this.timestamp_servico = timestamp_servico;
        this.concluido = concluido;
        this.cortesia = cortesia;
        this.servico = servico;
        this.funcionario = funcionario;
        this.estadia = estadia;
        this.quarto = quarto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTimestamp_servico() {
        return timestamp_servico;
    }

    public void setTimestamp_servico(String timestamp_servico) {
        this.timestamp_servico = timestamp_servico;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public boolean isCortesia() {
        return cortesia;
    }

    public void setCortesia(boolean cortesia) {
        this.cortesia = cortesia;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
    
    
}
