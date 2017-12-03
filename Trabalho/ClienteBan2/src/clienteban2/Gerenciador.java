/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2;

import clienteban2.tabelas.Cliente;
import clienteban2.tabelas.Estadia;
import clienteban2.tabelas.Funcionario;
import clienteban2.tabelas.Hotel;
import clienteban2.tabelas.Quarto;
import clienteban2.tabelas.Reserva;
import clienteban2.tabelas.Servico;
import clienteban2.tabelas.ServicoPrestado;
import clienteban2.tabelas.TipoQuarto;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gustavo
 */
public class Gerenciador {

    private final LinkedHashMap<Integer, Hotel> hoteis;
    public static final ArrayList<String> colunasHotel
            = new ArrayList(Arrays.asList("Código", "Nome", "Cidade", "Estado", "Endereço"));

    private final LinkedHashMap<Integer, TipoQuarto> tiposDeQuartos;

    private final ArrayList<Quarto> quartos;

    private final LinkedHashMap<Integer, Cliente> clientes;

    private final LinkedHashMap<Integer, Funcionario> funcionarios;

    private final LinkedHashMap<Integer, Reserva> reservas;
    private final LinkedHashMap<Integer, Estadia> estadias;
    private final LinkedHashMap<Integer, Servico> servicos;
    private final LinkedHashMap<Integer, ServicoPrestado> servicosPrestados;

    private static Gerenciador INSTANCIA;

    /**
     * Get the value of INSTANCIA
     *
     * @return the value of INSTANCIA
     */
    public static Gerenciador getInstancia() {
        return INSTANCIA;
    }

    public static void iniciateGerenciador() {
        if (INSTANCIA == null) {
            INSTANCIA = new Gerenciador();
        }
    }

    private Gerenciador() {
        this.hoteis = new LinkedHashMap<>();
        this.tiposDeQuartos = new LinkedHashMap<>();
        this.quartos = new ArrayList<>();
        this.clientes = new LinkedHashMap<>();
        this.funcionarios = new LinkedHashMap<>();
        this.reservas = new LinkedHashMap<>();
        this.estadias = new LinkedHashMap<>();
        this.servicos = new LinkedHashMap<>();
        this.servicosPrestados = new LinkedHashMap<>();
        refresh();
    }

    public void clear() {

        this.hoteis.clear();
        this.tiposDeQuartos.clear();
        this.quartos.clear();
        this.clientes.clear();
        this.funcionarios.clear();
        this.estadias.clear();
        this.reservas.clear();
        this.servicos.clear();
        this.servicosPrestados.clear();
    }

    public void refresh() {
        pushClientes();
        pushHoteis();
        pushTipoQuarto();
        pushQuartos();
        pushFuncionarios();
        pushReservas();
        pushEstadias();
        pushServicos();
        pushServicosPrestados();
    }

    private void pushEstadias() {
        try {
            ResultadoQuery conteudo = ClienteBan2
                    .getInstance().conexao
                    .RequestSelect("SELECT * FROM estadia;");
            conteudo.getDados().forEach((entry) -> {

                int codigo = Integer.parseInt(entry.get("est_pk_codigo"));

                String cIn = entry.get("est_dt_checkin");
                String cOut = entry.get("est_dt_checkout");

                int codigoReserva = Integer.parseInt(entry.get("res_fk_codigo"));
                int codigoCliente = Integer.parseInt(entry.get("cli_fk_codigo"));
                int codigoQuarto = Integer.parseInt(entry.get("qua_fk_numero"));
                int codigoHotel = Integer.parseInt(entry.get("hot_fk_codigo"));

                Quarto quarto = hoteis.get(codigoHotel).getQuartos().get(codigoQuarto);
                Cliente cliente = clientes.get(codigoCliente);
                Reserva reserva = reservas.get(codigoReserva);

                try {
                    // Nao tem, entao vamos criar um novo
                    Estadia sp = new Estadia(codigo, quarto, cliente, reserva, cIn, cOut);

                    this.estadias.put(codigo, sp);

                } catch (Exception ex) {
                    Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        } catch (Exception ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Estadia r : estadias.values()) {
            System.out.println(r);
        }
    }

    private void pushReservas() {
        try {
            ResultadoQuery conteudo = ClienteBan2
                    .getInstance().conexao
                    .RequestSelect("SELECT * FROM reserva;");
            conteudo.getDados().forEach((entry) -> {

                int codigo = Integer.parseInt(entry.get("res_pk_codigo"));

                String dataCria = entry.get("res_dt_dtcria");
                String dataPgto = entry.get("res_dt_pagamento");

                String checkIn = entry.get("res_dt_checkin");
                String checkOut = entry.get("res_dt_checkout");

                String estado = entry.get("res_st_estado");
                String camaExtra = entry.get("res_st_camaextra");

                int codigoCliente = Integer.parseInt(entry.get("cli_fk_codigo"));
                int codigoQuarto = Integer.parseInt(entry.get("qua_fk_numero"));
                int codigoHotel = Integer.parseInt(entry.get("hot_fk_codigo"));

                Quarto quarto = hoteis.get(codigoHotel).getQuartos().get(codigoQuarto);
                Cliente cliente = clientes.get(codigoCliente);

                try {
                    // Nao tem, entao vamos criar um novo
                    Reserva sp = new Reserva(codigo, quarto, cliente, dataCria, dataPgto, checkIn, checkOut, camaExtra.equals("S"), estado.charAt(0));

                    this.reservas.put(codigo, sp);

                } catch (Exception ex) {
                    Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        } catch (Exception ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Estadia r : estadias.values()) {
            System.out.println(r);
        }
    }

    private void pushServicos() {
        try {
            ResultadoQuery conteudo = ClienteBan2
                    .getInstance().conexao
                    .RequestSelect("SELECT * FROM Servico;");
            conteudo.getDados().forEach((entry) -> {

                int codigo = Integer.parseInt(entry.get("sev_pk_codigo"));
                String tipo = entry.get("sev_st_tipo");
                String descricao = entry.get("sev_st_descricao");

                float preco = Float.parseFloat(entry.get("sev_fl_preco"));

                try {
                    // Nao tem, entao vamos criar um novo
                    Servico sp = new Servico(codigo, tipo.charAt(0), descricao, preco);

                    this.servicos.put(codigo, sp);

                } catch (Exception ex) {
                    Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        } catch (Exception ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Servico r : servicos.values()) {
            System.out.println(r);
        }
    }

    private void pushServicosPrestados() {
        try {
            ResultadoQuery conteudo = ClienteBan2
                    .getInstance().conexao
                    .RequestSelect("SELECT * FROM Servico_Prestado;");
            conteudo.getDados().forEach((entry) -> {
                int codigo = Integer.parseInt(entry.get("svp_pk_codigo"));
                String dataServico = entry.get("svp_dt_dtservico");
                String concluido = entry.get("svp_st_concluido");
                String cortesia = entry.get("svp_st_cortesia");

                int codigoServico = Integer.parseInt(entry.get("sev_fk_codigo"));
                int codigoFuncionario = Integer.parseInt(entry.get("fun_fk_codigo"));
                int codigoEstadia = Integer.parseInt(entry.get("est_fk_codigo"));
                int codigoQuarto = Integer.parseInt(entry.get("qua_fk_numero"));
                int codigoHotel = Integer.parseInt(entry.get("hot_fk_hotel"));

                Quarto quarto = hoteis.get(codigoHotel).getQuartos().get(codigoQuarto);
                Estadia estadia = estadias.get(codigoEstadia);
                Servico servico = servicos.get(codigoServico);
                Funcionario funcionario = funcionarios.get(codigoFuncionario);

                try {
                    // Nao tem, entao vamos criar um novo
                    ServicoPrestado sp = new ServicoPrestado(codigo, dataServico, concluido.equals("C"), cortesia.equals("S"), servico, funcionario, estadia, quarto);

                    this.servicosPrestados.put(codigo, sp);

                } catch (Exception ex) {
                    Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        } catch (Exception ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (ServicoPrestado r : servicosPrestados.values()) {
            System.out.println(r);
        }
    }

    private void pushFuncionarios() {
        try {
            ResultadoQuery conteudo = ClienteBan2
                    .getInstance().conexao
                    .RequestSelect("SELECT * FROM funcionario order by (fun_pk_codigo);");
            conteudo.getDados().forEach((entry) -> {
                int codigo = Integer.parseInt(entry.get("fun_pk_codigo"));
                String nome = entry.get("fun_st_nome");
                String telefone = entry.get("fun_st_telefone");
                String cpf = entry.get("fun_st_cpf");
                String local = entry.get("fun_st_endereco");
                String cargo = entry.get("fun_st_cargo");
                char estado = entry.get("fun_st_estado").charAt(0);

                Hotel hotel = null;

                if (entry.containsKey("hot_fk_hotel")) {
                    int codigoH = Integer.parseInt(entry.get("hot_fk_hotel"));
                    hotel = hoteis.get(codigoH);
                }

                if (this.funcionarios.containsKey(codigo)) {
                    // Tem, entao vamos atualizar em vez de criar
                    Funcionario fun = this.funcionarios.get(codigo);

                    if (hotel != null) {
                        if (hotel != fun.getHotel()) {
                            if (fun.getHotel() != null) {
                                if (fun.getHotel().getFuncionarios().containsKey(fun.getCodigo())) {
                                    fun.getHotel().getFuncionarios().remove(fun.getCodigo());
                                }
                            }
                            fun.setHotel(hotel);
                            hotel.addFuncionario(fun);
                        }
                    }

                    fun.setNome(nome);
                    fun.setTelefone(telefone);
                    fun.setCpf(cpf);
                    fun.setEndereco(local);
                    fun.setCargo(cargo);
                    fun.setHotel(hotel);
                    fun.setEstado(estado);

                } else {
                    try {
                        // Nao tem, entao vamos criar um novo
                        Funcionario fun = new Funcionario(codigo, nome, telefone, cpf, local, cargo, estado, hotel);
                        if (hotel != null) {
                            hotel.addFuncionario(fun);
                        }
                        this.funcionarios.put(codigo, fun);

                    } catch (Exception ex) {
                        Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

        } catch (Exception ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pushClientes() {
        try {
            ResultadoQuery conteudo = ClienteBan2
                    .getInstance().conexao
                    .RequestSelect("SELECT * FROM cliente order by (cli_pk_codigo);");
            conteudo.getDados().forEach((entry) -> {
                int codigo = Integer.parseInt(entry.get("cli_pk_codigo"));
                String nome = entry.get("cli_st_nome");
                String telefone = entry.get("cli_st_telefone");
                String cpf = entry.get("cli_st_cpf");
                String local = entry.get("cli_st_endereco");

                if (this.clientes.containsKey(codigo)) {
                    // Tem, entao vamos atualizar em vez de criar
                    Cliente cli = this.clientes.get(codigo);

                    cli.setNome(nome);
                    cli.setTelefone(telefone);
                    cli.setCpf(cpf);
                    cli.setEndereco(local);

                } else {
                    try {
                        // Nao tem, entao vamos criar um novo
                        Cliente cli = new Cliente(codigo, nome, telefone, cpf, local);
                        this.clientes.put(codigo, cli);

                    } catch (Exception ex) {
                        Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

        } catch (Exception ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pushTipoQuarto() {
        try {
            ResultadoQuery conteudo = ClienteBan2.getInstance().conexao.RequestSelect("SELECT t.*, hot_pfk_codigo, qpr_in_diaria  FROM Tipo_Quarto t JOIN quarto_preco p ON p.tqu_pfk_codigo = tqu_pk_codigo ORDER BY (hot_pfk_codigo, tqu_pk_codigo);");
            conteudo.getDados().forEach((entry) -> {
                int codigo = Integer.parseInt(entry.get("tqu_pk_codigo"));
                String nome = entry.get("tqu_st_tipo");

                int codigoHotel = Integer.parseInt(entry.get("hot_pfk_codigo"));
                float preco = Float.parseFloat(entry.get("qpr_in_diaria"));

                Hotel hotel = hoteis.get(codigoHotel);

                try {
                    TipoQuarto tquarto = new TipoQuarto(codigo, nome);

                    if (!tquarto.getPrecos().containsKey(hotel)) {
                        tquarto.getPrecos().put(hotel, preco);
                    }

                    this.tiposDeQuartos.put(codigo, tquarto);
                } catch (Exception ex) {
                    Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        } catch (Exception ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pushQuartos() {
        try {
            ResultadoQuery conteudo = ClienteBan2
                    .getInstance().conexao
                    .RequestSelect("SELECT q.*, qp.qpr_in_diaria FROM quarto q JOIN Quarto_Preco qp ON qp.tqu_pfk_codigo = q.tqu_fk_codigo AND q.hot_pfk_codigo = qp.hot_pfk_codigo order by (qua_pk_numero);");
            conteudo.getDados().forEach((entry) -> {
                int codigo = Integer.parseInt(entry.get("qua_pk_numero"));
                int codigoTipo = Integer.parseInt(entry.get("tqu_fk_codigo"));
                int codigoHotel = Integer.parseInt(entry.get("hot_pfk_codigo"));
                float preco = Float.parseFloat(entry.get("qpr_in_diaria"));
                String local = entry.get("qua_in_local");

                boolean temosQuarto = false;
                Quarto quarto = null;

                TipoQuarto tq = this.tiposDeQuartos.get(codigoTipo);
                Hotel hotel = this.hoteis.get(codigoHotel);

                for (Quarto q : quartos) {
                    if (q.getNumero() == codigo && q.getHotel().getCodigo() == codigoHotel) {
                        temosQuarto = true;
                        quarto = q;
                        break;
                    }
                }

                if (temosQuarto) {
                    // Tem, entao vamos atualizar em vez de criar
                    if (hotel != quarto.getHotel()) {
                        quarto.getHotel().getQuartos().remove(quarto.getNumero());
                        quarto.setHotel(hotel);
                        hotel.addQuarto(quarto);
                    }

                    quarto.setTipo(tq);
                    quarto.setLocal(local);
                    quarto.setPreco(preco);

                } else {
                    try {
                        // Nao tem, entao vamos criar um novo

                        quarto = new Quarto(codigo, local, hotel, tq, preco);

                        hotel.addQuarto(quarto);

                        this.quartos.add(quarto);

                    } catch (Exception ex) {
                        Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

        } catch (Exception ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pushHoteis() {
        try {
            ResultadoQuery conteudo = ClienteBan2.getInstance().conexao.RequestSelect("SELECT * FROM hotel order by (hot_pk_codigo);");
            conteudo.getDados().forEach((entry) -> {
                int codigo = Integer.parseInt(entry.get("hot_pk_codigo"));
                int estrelas = Integer.parseInt(entry.get("hot_in_estrelas"));
                String nome = entry.get("hot_st_nome");
                String cnpj = entry.get("hot_st_cnpj");
                String endereco = entry.get("hot_st_endereco");
                String cidade = entry.get("hot_st_cidade");
                String uf = entry.get("hot_st_uf");

                if (this.hoteis.containsKey(codigo)) {
                    // Tem, entao vamos atualizar em vez de criar
                    Hotel hotel = this.hoteis.get(codigo);

                    hotel.setCidade(cidade);
                    hotel.setCnpj(cnpj);
                    hotel.setEndereco(endereco);
                    hotel.setEstrelas(estrelas);
                    hotel.setNome(nome);
                    hotel.setUF(uf);
                } else {
                    try {
                        // Nao tem, entao vamos criar um novo
                        Hotel hotel = Hotel.criarHotel(codigo, nome, cnpj, cidade, uf, endereco, estrelas);
                        this.hoteis.put(codigo, hotel);
                    } catch (Exception ex) {
                        Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

        } catch (Exception ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addHotel(Hotel h) {
        hoteis.put(h.getCodigo(), h);
    }

    public LinkedHashMap<Integer, Hotel> getHoteis() {
        return hoteis;
    }

    public void addCliente(Cliente c) {
        clientes.put(c.getCodigo(), c);
    }

    public LinkedHashMap<Integer, Cliente> getClientes() {
        return clientes;
    }

    public LinkedHashMap<Integer, TipoQuarto> getTipoDeQuartos() {
        return this.tiposDeQuartos;
    }

    public LinkedHashMap<Integer, Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    void addQuarto(Quarto quarto) {
        quartos.add(quarto);
    }

    public ArrayList<Quarto> getQuartos() {
        return quartos;
    }

    public LinkedHashMap<Integer, Reserva> getReservas() {
        return reservas;
    }

    public LinkedHashMap<Integer, Estadia> getEstadias() {
        return estadias;
    }

    public LinkedHashMap<Integer, Servico> getServicos() {
        return servicos;
    }

    public LinkedHashMap<Integer, ServicoPrestado> getServicosPrestados() {
        return servicosPrestados;
    }

    void addFuncionario(Funcionario funcionario) {
        funcionarios.put(funcionario.getCodigo(), funcionario);
    }

    void addReserva(Reserva reserva) {
        reservas.put(reserva.getCodigo(), reserva);
    }

}
