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

    public static Reserva criarReserva(Quarto quarto, Cliente cliente, String timestamp_criacao, String timestamp_pagamento, String checkIn, String checkOut, boolean camaExtra, char estado) {
        return new Reserva(getNextId(), quarto, cliente, timestamp_criacao, timestamp_pagamento, checkIn, checkOut, camaExtra, estado);
    }
    
    public static int getNextId(){
        int lastId = 0;
        for (int res : Gerenciador.getInstancia().getReservas().keySet()){
            if (lastId < res){
                lastId = res;
            }
        }
        return lastId + 1;
    }
    
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
        return "Reserva: de " + cliente + ", no quarto " + quarto + ", de " + checkIn + " ate " + checkOut + ". Criado as " + this.timestamp_criacao + " e pagamento as " + this.timestamp_pagamento;
    }
    
    public static void verificaDados(String timestamp_pagamento, String checkIn, String checkOut) throws Exception {
        if (timestamp_pagamento.equals("  -  -    ")){
            throw new Exception("Data de pagamento precisa ser preenchida!");
        }
        if (checkIn.equals("  -  -    ")){
            throw new Exception("Data de Check In precisa ser preenchida!");
        }
        if (checkOut.equals("  -  -    ")){
            throw new Exception("Data de Check Out precisa ser preenchida!");
        }
        
    }

    public String insertQuery() throws Exception {
        verificaDados(timestamp_pagamento, checkIn, checkOut);
        return "INSERT INTO reserva VALUES (" + this.codigo
                + ", \'" + this.quarto.getNumero() + "\'"
                + ", \'" + this.quarto.getHotel().getCodigo() + "\'"
                + ", \'" + this.cliente.getCodigo() + "\'"
                + ", \'" + this.timestamp_criacao + "\'"
                + ", \'" + this.timestamp_pagamento + "\'"
                + ", \'" + this.checkIn + "\'"
                + ", \'" + this.checkOut + "\'"
                + ", \'" + (this.camaExtra ? "S" : "N") + "\'"
                + ", \'" + this.estado + "\');";
    }

    public String updateQuery() throws Exception {
        verificaDados(timestamp_pagamento, checkIn, checkOut);
        return "UPDATE reserva SET "
                + "qua_fk_numero = \'" + this.quarto.getNumero() + "\'"
                + ", hot_fk_codigo = \'" + this.quarto.getHotel().getCodigo() + "\'"
                + ", cli_fk_codigo = \'" + this.cliente.getCodigo() + "\'"
                + ", res_dt_dtcria = \'" + this.timestamp_criacao + "\'"
                + ", res_dt_pagamento = \'" + this.timestamp_pagamento + "\'"
                + ", res_dt_checkin = \'" + this.checkIn + "\'"
                + ", res_dt_checkout = \'" + this.checkOut + "\'"
                + ", res_st_camaextra = \'" + (this.camaExtra ? "S" : "N") + "\'"
                + ", res_st_estado = \'" + this.estado + "\' WHERE res_pk_codigo = " + codigo + ";";
    }
}
