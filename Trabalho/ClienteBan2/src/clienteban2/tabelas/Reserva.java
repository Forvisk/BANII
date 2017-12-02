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
public class Reserva {

    private int codigo;

    private Quarto quarto;
    private Cliente cliente;

    private String timestamp_criacao;
    private String timestamp_pagamento;

    private String checkIn;
    private String checkOut;
    
    private boolean camaExtra;
    private char estado; // E - esperando pagamento; S - pagamento aceito; C - cancelado pelo cliente; A - anulado pela agencia (sem pagamento apos 48 horas);

    public Reserva(int codigo, Quarto quarto, Cliente cliente, String timestamp_criacao, String timestamp_pagamento, String checkIn, String checkOut, boolean camaExtra, char estado) {
        this.codigo = codigo;
        this.quarto = quarto;
        this.cliente = cliente;
        this.timestamp_criacao = timestamp_criacao;
        this.timestamp_pagamento = timestamp_pagamento;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.camaExtra = camaExtra;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTimestamp_criacao() {
        return timestamp_criacao;
    }

    public void setTimestamp_criacao(String timestamp_criacao) {
        this.timestamp_criacao = timestamp_criacao;
    }

    public String getTimestamp_pagamento() {
        return timestamp_pagamento;
    }

    public void setTimestamp_pagamento(String timestamp_pagamento) {
        this.timestamp_pagamento = timestamp_pagamento;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public boolean isCamaExtra() {
        return camaExtra;
    }

    public void setCamaExtra(boolean camaExtra) {
        this.camaExtra = camaExtra;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String toString(){
        return "Reserva: de " + cliente + ", no quarto " + quarto + ", de " + checkIn.toString() + " ate " + checkOut.toString() + ". Criado as " + this.timestamp_criacao + " e pagamento as " + this.timestamp_pagamento;
    }
}
