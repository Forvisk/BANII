/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2.tabelas;

import java.sql.Date;

/**
 *
 * @author gustavo
 */
public class Estadia {
    private int codigo;
    
    private Quarto quarto;
    private Cliente cliente;
    private Reserva reserva;
    
    private String checkIn;
    private String checkOut;

    public Estadia(int codigo, Quarto quarto, Cliente cliente, Reserva reserva, String checkIn, String checkOut) {
        this.codigo = codigo;
        this.quarto = quarto;
        this.cliente = cliente;
        this.reserva = reserva;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
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
    
    
}
