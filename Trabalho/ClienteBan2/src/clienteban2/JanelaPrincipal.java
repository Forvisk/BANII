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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gustavo
 */
public class JanelaPrincipal extends javax.swing.JFrame {

    private static JanelaPrincipal INSTANCIA;

    /**
     * Get the value of INSTANCIA
     *
     * @return the value of INSTANCIA
     */
    public static JanelaPrincipal getInstancia() {
        return INSTANCIA;
    }

    public static void iniciateJanelaPrincipal() {
        if (INSTANCIA == null) {
            INSTANCIA = new JanelaPrincipal();
        }
    }

    DefaultTableModel modeloTabela1;
    DefaultTableModel modeloTabelaHQ;
    DefaultTableModel modeloTabelaHF;
    DefaultTableModel modeloTabelaFuncionarios;
    DefaultTableModel modeloTabelaReserva;
    DefaultTableModel modeloTabelaClientes;
    DefaultTableModel modeloTabelaEstadia;
    DefaultTableModel modeloTabelaServicos;
    DefaultTableModel modeloTabelaSPrestados;
    int tabela1PKColumnIndex = 1;

    /**
     * Creates new form JanelaPrincipal
     */
    private JanelaPrincipal() {
        initComponents();

        modeloTabela1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        modeloTabelaHF = (DefaultTableModel) tHFuncionarios.getModel();
        modeloTabelaClientes = (DefaultTableModel) tClientes.getModel();
        modeloTabelaHQ = (DefaultTableModel) tHQuartos.getModel();
        modeloTabelaFuncionarios = (DefaultTableModel) tFuncionario.getModel();
        modeloTabelaReserva = (DefaultTableModel) tReserva.getModel();
        modeloTabelaEstadia = (DefaultTableModel) tEstadia.getModel();
        modeloTabelaServicos = (DefaultTableModel) tServicos.getModel();
        modeloTabelaSPrestados = (DefaultTableModel) tSPrestados.getModel();
        tTabela1.setModel(modeloTabela1);

        setupTabela();

    }

    public void setupTabela() {
        Gerenciador.getInstancia().refresh();
        setTabela1();
        setTabelaClientes();
        setTabelaFuncionarios();
        setTabelaReserva();
        setTabelaEstadia();
        setTabelaServicos();
        setTabelaServicosPrestados();
    }

    private void setTabelaServicosPrestados() {
        modeloTabelaSPrestados.setRowCount(0);
        try {
            Gerenciador.getInstancia().getServicosPrestados().values().stream().map((res) -> {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(res.getCodigo()));
                row.add(res.isConcluido() ? "Finalizado" : "Em andamento");
                row.add(res.getTimestamp_servico());
                row.add(res.isCortesia() ? "Sim" : "Não");
                row.add(res.getServico().getDescricao());
                row.add(res.getFuncionario().getNome());
                row.add(res.getQuarto().getHotel().getNome() + "(" + res.getQuarto().getNumero() + ")");
                return row;
            }).forEachOrdered((row) -> {
                modeloTabelaSPrestados.addRow(row.toArray());
            });
        } catch (Exception ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        tSPrestados.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        ListSelectionModel selectionModel = tSPrestados.getSelectionModel();
        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            try {
                int selectedRowNum = tSPrestados.getSelectedRowCount();
                if (selectedRowNum == 1) {

                    int codigo = Integer.valueOf(tSPrestados.getValueAt(tSPrestados.getSelectedRow(), 0).toString());
                    Estadia estadia = Gerenciador.getInstancia().getEstadias().get(codigo);

                    jSPDeletar.setEnabled(true);
                    jSPDeletar.setToolTipText("Clique para deletar!");

                } else if (selectedRowNum > 1) {
                    jSPDeletar.setEnabled(true);
                    jSPDeletar.setToolTipText("Clique para deletar essas " + selectedRowNum + " linhas!");

                } else {

                    jSPDeletar.setEnabled(false);
                    jSPDeletar.setToolTipText("Selecione uma linha!");
                }
            } catch (Exception ex) {

            }
        });

    }

    private void setTabelaServicos() {
        modeloTabelaServicos.setRowCount(0);
        try {
            Gerenciador.getInstancia().getServicos().values().stream().map((res) -> {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(res.getCodigo()));
                row.add(res.getTipo() + "");
                row.add(res.getDescricao());
                row.add(res.getPreco() + "");
                return row;
            }).forEachOrdered((row) -> {
                modeloTabelaServicos.addRow(row.toArray());
            });
        } catch (Exception ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        tServicos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        ListSelectionModel selectionModel = tServicos.getSelectionModel();
        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            try {
                int selectedRowNum = tServicos.getSelectedRowCount();
                if (selectedRowNum == 1) {

                    int codigo = Integer.valueOf(tServicos.getValueAt(tServicos.getSelectedRow(), 0).toString());
                    Estadia estadia = Gerenciador.getInstancia().getEstadias().get(codigo);

                    jSDeletar.setEnabled(true);
                    jSDeletar.setToolTipText("Clique para deletar!");

                } else if (selectedRowNum > 1) {
                    jSDeletar.setEnabled(true);
                    jSDeletar.setToolTipText("Clique para deletar essas " + selectedRowNum + " linhas!");

                } else {

                    jSDeletar.setEnabled(false);
                    jSDeletar.setToolTipText("Selecione uma linha!");
                }
            } catch (Exception ex) {

            }
        });

    }

    private void setTabelaEstadia() {
        modeloTabelaEstadia.setRowCount(0);
        try {
            Gerenciador.getInstancia().getEstadias().values().stream().map((res) -> {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(res.getCodigo()));
                row.add(res.getCliente().getNome());
                row.add(res.getQuarto().getHotel().getNome() + "(" + res.getQuarto().getNumero() + ")");
                row.add(res.getCheckIn());
                row.add(res.getCheckOut());
                row.add(res.getReserva() != null ? "" + res.getReserva().getCodigo() : "Não");
                return row;
            }).forEachOrdered((row) -> {
                modeloTabelaEstadia.addRow(row.toArray());
            });
        } catch (Exception ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        tEstadia.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        ListSelectionModel selectionModel = tEstadia.getSelectionModel();
        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            try {
                int selectedRowNum = tEstadia.getSelectedRowCount();
                if (selectedRowNum == 1) {

                    int codigo = Integer.valueOf(tEstadia.getValueAt(tEstadia.getSelectedRow(), 0).toString());
                    Estadia estadia = Gerenciador.getInstancia().getEstadias().get(codigo);

                    jEDeletar.setEnabled(true);
                    jEDeletar.setToolTipText("Clique para deletar!");

                } else if (selectedRowNum > 1) {
                    jEDeletar.setEnabled(true);
                    jEDeletar.setToolTipText("Clique para deletar essas " + selectedRowNum + " linhas!");

                } else {

                    jEDeletar.setEnabled(false);
                    jEDeletar.setToolTipText("Selecione uma linha!");
                }
            } catch (Exception ex) {

            }
        });
    }

    private void setTabelaReserva() {
        modeloTabelaReserva.setRowCount(0);
        try {
            Gerenciador.getInstancia().getReservas().values().stream().map((res) -> {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(res.getCodigo()));
                row.add(res.getQuarto().getHotel().getNome() + "(" + res.getQuarto().getNumero() + ")");
                row.add(res.getCliente().getNome());
                row.add(res.getCheckIn());
                row.add(res.getCheckOut());
                row.add(res.getTimestamp_criacao());
                row.add(res.getTimestamp_pagamento());
                row.add(res.isCamaExtra() ? "Sim" : "Não");
                row.add(res.getEstado() + "");
                return row;
            }).forEachOrdered((row) -> {
                modeloTabelaReserva.addRow(row.toArray());
            });
        } catch (Exception ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        tReserva.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        ListSelectionModel selectionModel = tReserva.getSelectionModel();
        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            try {
                int selectedRowNum = tReserva.getSelectedRowCount();
                if (selectedRowNum == 1) {

                    int codigo = Integer.valueOf(tReserva.getValueAt(tReserva.getSelectedRow(), 0).toString());
                    Reserva reserva = Gerenciador.getInstancia().getReservas().get(codigo);

                    JRCamaExtra.setText(reserva.isCamaExtra() ? "Sim" : "Não");
                    JREstado.setText(reserva.getEstado() + "");
                    JRQuarto.setText(reserva.getQuarto().getLocal() + " N. " + reserva.getQuarto().getNumero());
                    jRCheckIn.setText(reserva.getCheckIn());
                    jRCheckOut.setText(reserva.getCheckOut());
                    jRCliente.setText(reserva.getCliente().getNome());
                    jRHotel.setText(reserva.getQuarto().getHotel().getNome());

                    jRDeletar.setEnabled(true);
                    jRDeletar.setToolTipText("Clique para deletar!");

                } else if (selectedRowNum > 1) {
                    jRDeletar.setEnabled(true);
                    jRDeletar.setToolTipText("Clique para deletar essas " + selectedRowNum + " linhas!");

                    JRCamaExtra.setText("");
                    JREstado.setText("");
                    JRQuarto.setText("");
                    jRCheckIn.setText("");
                    jRCheckOut.setText("");
                    jRCliente.setText("");
                    jRHotel.setText("");

                    // 0 selected
                } else {
                    JRCamaExtra.setText("");
                    JREstado.setText("");
                    JRQuarto.setText("");
                    jRCheckIn.setText("");
                    jRCheckOut.setText("");
                    jRCliente.setText("");
                    jRHotel.setText("");

                    jRDeletar.setEnabled(false);
                    jRDeletar.setToolTipText("Selecione uma linha!");
                }
            } catch (Exception ex) {

            }
        });
    }

    private void setTabelaFuncionarios() {
        modeloTabelaFuncionarios.setRowCount(0);
        try {
            Gerenciador.getInstancia().getFuncionarios().values().stream().map((cliente) -> {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(cliente.getCodigo()));
                row.add(cliente.getNome());
                row.add(cliente.getTelefone());
                row.add(cliente.getCargo());
                row.add("" + cliente.getEstado());
                row.add(cliente.getHotel() == null ? "Nenhum" : cliente.getHotel().getNome());
                return row;
            }).forEachOrdered((row) -> {
                modeloTabelaFuncionarios.addRow(row.toArray());
            });
        } catch (Exception ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        tFuncionario.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        ListSelectionModel selectionModel = tFuncionario.getSelectionModel();
        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            try {
                int selectedRowNum = tFuncionario.getSelectedRowCount();
                if (selectedRowNum == 1) {

                    int codigo = Integer.valueOf(tFuncionario.getValueAt(tFuncionario.getSelectedRow(), 0).toString());
                    Cliente cli = Gerenciador.getInstancia().getClientes().get(codigo);

                    jFDeletar.setEnabled(true);
                    jFDeletar.setToolTipText("Clique para deletar!");

                    jFEditar.setEnabled(true);
                    jFEditar.setToolTipText("Clique para salvar os dados!");

                } else if (selectedRowNum > 1) {
                    jFDeletar.setEnabled(true);
                    jFDeletar.setToolTipText("Clique para deletar essas " + selectedRowNum + " linhas!");

                    jFEditar.setEnabled(false);
                    jFEditar.setToolTipText("Não é possivel salvar mais de um por vez!");

                    // 0 selected
                } else {
                    jFEditar.setEnabled(false);
                    jFEditar.setToolTipText("Selecione uma linha!");

                    jFDeletar.setEnabled(false);
                    jFDeletar.setToolTipText("Selecione uma linha!");
                }
            } catch (Exception ex) {

            }
        });
    }

    private void setTabelaClientes() {
        modeloTabelaClientes.setRowCount(0);
        try {
            Gerenciador.getInstancia().getClientes().values().stream().map((cliente) -> {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(cliente.getCodigo()));
                row.add(cliente.getNome());
                row.add(cliente.getCpf());
                row.add(cliente.getEndereco());
                row.add(cliente.getTelefone());
                return row;
            }).forEachOrdered((row) -> {
                modeloTabelaClientes.addRow(row.toArray());
            });
        } catch (Exception ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        tClientes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        ListSelectionModel selectionModel = tClientes.getSelectionModel();
        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            try {
                int selectedRowNum = tClientes.getSelectedRowCount();
                if (selectedRowNum == 1) {

                    int codigo = Integer.valueOf(tClientes.getValueAt(tClientes.getSelectedRow(), 0).toString());
                    Cliente cli = Gerenciador.getInstancia().getClientes().get(codigo);

                    jCDeletar.setEnabled(true);
                    jCDeletar.setToolTipText("Clique para deletar!");

                    jCAtualizar.setEnabled(true);
                    jCAtualizar.setToolTipText("Clique para salvar os dados!");

                } else if (selectedRowNum > 1) {
                    jCDeletar.setEnabled(true);
                    jCDeletar.setToolTipText("Clique para deletar essas " + selectedRowNum + " linhas!");

                    jCAtualizar.setEnabled(false);
                    jCAtualizar.setToolTipText("Não é possivel salvar mais de um por vez!");

                    // 0 selected
                } else {
                    jCAtualizar.setEnabled(false);
                    jCAtualizar.setToolTipText("Selecione uma linha!");

                    jCDeletar.setEnabled(false);
                    jCDeletar.setToolTipText("Selecione uma linha!");
                }
            } catch (Exception ex) {

            }
        });

    }

    private void setTabela1() {
        modeloTabela1.setRowCount(0);
        modeloTabela1.setColumnCount(0);
        try {

            Gerenciador.getInstancia().refresh();

            Gerenciador.colunasHotel.forEach((coluna) -> {
                modeloTabela1.addColumn(coluna);
            });

            Gerenciador.getInstancia().getHoteis().values().stream().map((hotel) -> {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(hotel.getCodigo()));
                row.add(hotel.getNome());
                row.add(hotel.getCidade());
                row.add(hotel.getUF());
                row.add(hotel.getEndereco());
                return row;
            }).forEachOrdered((row) -> {
                modeloTabela1.addRow(row.toArray());
            });

        } catch (Exception ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        tTabela1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        ListSelectionModel selectionModel = tTabela1.getSelectionModel();

        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            modeloTabelaHQ.setRowCount(0);
            modeloTabelaHF.setRowCount(0);
            try {
                int selectedRowNum = tTabela1.getSelectedRowCount();
                if (selectedRowNum == 1) {

                    int codigo = Integer.valueOf(tTabela1.getValueAt(tTabela1.getSelectedRow(), 0).toString());

                    Hotel hotel = Gerenciador
                            .getInstancia()
                            .getHoteis()
                            .get(codigo);

                    for (Quarto quarto : hotel.getQuartos().values()) {
                        ArrayList<String> row = new ArrayList<>();
                        row.add(String.valueOf(quarto.getNumero()));
                        row.add(quarto.getTipo().getNome());
                        row.add(String.valueOf(quarto.getPreco()));
                        row.add(quarto.isVago());
                        modeloTabelaHQ.addRow(row.toArray());
                    }

                    for (Funcionario fun : hotel.getFuncionarios().values()) {
                        System.out.println(fun);
                        ArrayList<String> row = new ArrayList<>();
                        row.add(String.valueOf(fun.getCodigo()));
                        row.add(fun.getNome());
                        row.add(fun.getCargo());
                        row.add(String.valueOf(fun.getEstado()));
                        modeloTabelaHF.addRow(row.toArray());
                    }

                    hotelCNPJText.setText(hotel.getCnpj());
                    hoteCidadeText.setText(hotel.getCidade());
                    hoteEstrelasBox.setSelectedItem(String.valueOf(hotel.getEstrelas()));
                    hoteUFText.setText(hotel.getUF());
                    hotelCodigoText.setText(String.valueOf(hotel.getCodigo()));
                    hotelNomeText.setText(hotel.getNome());
                    hotelEnderecoText.setText(hotel.getEndereco());

                    bDeletar1.setEnabled(true);
                    bDeletar1.setToolTipText("Clique para deletar!");

                    bAtualizar1.setEnabled(true);
                    bAtualizar1.setToolTipText("Clique para salvar os dados!");

                    jCriaQuarto.setEnabled(true);
                    jCriaQuarto.setToolTipText("Clique para adicionar um quarto!");

                } else if (selectedRowNum > 1) {
                    bDeletar1.setEnabled(true);
                    bDeletar1.setToolTipText("Clique para deletar essas " + selectedRowNum + " linhas!");

                    bAtualizar1.setEnabled(false);
                    bAtualizar1.setToolTipText("Não é possivel salvar mais de um por vez!");

                    jCriaQuarto.setEnabled(false);
                    jCriaQuarto.setToolTipText("Selecione apenas um hotel por vez para criar um quarto!");
                    // 0 selected
                } else {
                    bAtualizar1.setEnabled(false);
                    bAtualizar1.setToolTipText("Selecione uma linha!");

                    bDeletar1.setEnabled(false);
                    bDeletar1.setToolTipText("Selecione uma linha!");

                    jCriaQuarto.setEnabled(false);
                    jCriaQuarto.setToolTipText("Seleciene um hotel para criar um quarto!");
                }
            } catch (Exception ex) {

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTabela1 = new javax.swing.JTable();
        bCriar1 = new javax.swing.JButton();
        bAtualizar1 = new javax.swing.JButton();
        bDeletar1 = new javax.swing.JButton();
        bReload1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        hotelCodigoText = new javax.swing.JTextField();
        hotelCNPJText = new javax.swing.JTextField();
        hotelNomeText = new javax.swing.JTextField();
        hoteEstrelasBox = new javax.swing.JComboBox<>();
        hotelEnderecoText = new javax.swing.JTextField();
        hoteCidadeText = new javax.swing.JTextField();
        hoteUFText = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tHQuartos = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jCriaQuarto = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tHFuncionarios = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tClientes = new javax.swing.JTable();
        jCAdicionarCliente = new javax.swing.JButton();
        jCDeletar = new javax.swing.JButton();
        jCAtualizar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tFuncionario = new javax.swing.JTable();
        jFDeletar = new javax.swing.JButton();
        jFEditar = new javax.swing.JButton();
        jFAdicionar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tReserva = new javax.swing.JTable();
        jRDeletar = new javax.swing.JButton();
        jRAdicionar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jRCheckOut = new javax.swing.JLabel();
        jRCheckIn = new javax.swing.JLabel();
        jRCliente = new javax.swing.JLabel();
        jRHotel = new javax.swing.JLabel();
        JRQuarto = new javax.swing.JLabel();
        JRCamaExtra = new javax.swing.JLabel();
        JREstado = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tEstadia = new javax.swing.JTable();
        jEDeletar = new javax.swing.JButton();
        jEAdicionar = new javax.swing.JButton();
        jECheckOut = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tServicos = new javax.swing.JTable();
        jSDeletar = new javax.swing.JButton();
        jSAdicionar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tSPrestados = new javax.swing.JTable();
        jSPDeletar = new javax.swing.JButton();
        jSPAdicionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tTabela1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tTabela1);

        bCriar1.setText("Criar");
        bCriar1.setToolTipText("Abre uma janela para acrescentar novos dados");
        bCriar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCriar1ActionPerformed(evt);
            }
        });

        bAtualizar1.setText("Salvar Mudanças");
        bAtualizar1.setToolTipText("Clique para salvar!");
        bAtualizar1.setEnabled(false);
        bAtualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtualizar1ActionPerformed(evt);
            }
        });

        bDeletar1.setText("Deletar");
        bDeletar1.setToolTipText("Clique para deletar!");
        bDeletar1.setEnabled(false);
        bDeletar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeletar1ActionPerformed(evt);
            }
        });

        bReload1.setText("Atualiza dados");
        bReload1.setToolTipText("Faz uma requisição de atualização para o Banco de Dados");
        bReload1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReload1ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));

        jLabel1.setText("Código:");

        jLabel2.setText("CNPJ:");

        jLabel3.setText("Nome:");

        jLabel4.setText("Endereço:");

        jLabel5.setText("Cidade:");

        jLabel6.setText("Estado:");

        jLabel7.setText("Estrelas:");

        hotelCodigoText.setEditable(false);

        hoteEstrelasBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        tHQuartos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipo", "Preço", "Vago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tHQuartos);

        jLabel8.setText("Quartos:");

        jCriaQuarto.setText("Adicionar Quarto");
        jCriaQuarto.setEnabled(false);
        jCriaQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCriaQuartoActionPerformed(evt);
            }
        });

        jLabel9.setText("Funcionarios:");

        tHFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Cargo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tHFuncionarios);
        if (tHFuncionarios.getColumnModel().getColumnCount() > 0) {
            tHFuncionarios.getColumnModel().getColumn(0).setMinWidth(30);
            tHFuncionarios.getColumnModel().getColumn(0).setMaxWidth(50);
            tHFuncionarios.getColumnModel().getColumn(3).setMinWidth(40);
            tHFuncionarios.getColumnModel().getColumn(3).setMaxWidth(50);
        }

        jButton2.setText("Adicionar Funcionário");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(25, 25, 25))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hoteEstrelasBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hotelCodigoText, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hotelEnderecoText)
                    .addComponent(hotelNomeText)
                    .addComponent(hoteCidadeText)
                    .addComponent(hoteUFText, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(hotelCNPJText))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCriaQuarto)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(hotelCodigoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jCriaQuarto)
                    .addComponent(jLabel9)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(hotelCNPJText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(hotelNomeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(hoteEstrelasBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(hotelEnderecoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hoteCidadeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hoteUFText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bReload1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCriar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bAtualizar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bDeletar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bDeletar1)
                    .addComponent(bAtualizar1)
                    .addComponent(bCriar1)
                    .addComponent(bReload1)))
        );

        jTabbedPane1.addTab("Hoteis", jPanel1);

        tClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Telefone", "Endereço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tClientes);
        if (tClientes.getColumnModel().getColumnCount() > 0) {
            tClientes.getColumnModel().getColumn(0).setMinWidth(30);
            tClientes.getColumnModel().getColumn(0).setMaxWidth(50);
            tClientes.getColumnModel().getColumn(1).setMaxWidth(300);
            tClientes.getColumnModel().getColumn(2).setMinWidth(200);
            tClientes.getColumnModel().getColumn(2).setMaxWidth(240);
            tClientes.getColumnModel().getColumn(3).setMaxWidth(300);
            tClientes.getColumnModel().getColumn(4).setMaxWidth(300);
        }

        jCAdicionarCliente.setText("Adicionar");
        jCAdicionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCAdicionarClienteActionPerformed(evt);
            }
        });

        jCDeletar.setText("Deletar");
        jCDeletar.setEnabled(false);
        jCDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCDeletarActionPerformed(evt);
            }
        });

        jCAtualizar.setText("Editar");
        jCAtualizar.setEnabled(false);
        jCAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1343, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCAdicionarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCDeletar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCAtualizar)
                    .addComponent(jCDeletar)
                    .addComponent(jCAdicionarCliente))
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Clientes", jPanel2);

        tFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Telefone", "Cargo", "Estado", "Hotel"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tFuncionario);
        if (tFuncionario.getColumnModel().getColumnCount() > 0) {
            tFuncionario.getColumnModel().getColumn(0).setMaxWidth(50);
            tFuncionario.getColumnModel().getColumn(4).setMaxWidth(50);
        }

        jFDeletar.setText("Deletar");
        jFDeletar.setEnabled(false);
        jFDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFDeletarActionPerformed(evt);
            }
        });

        jFEditar.setText("Editar");
        jFEditar.setEnabled(false);
        jFEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFEditarActionPerformed(evt);
            }
        });

        jFAdicionar.setText("Adicionar");
        jFAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1343, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jFAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFDeletar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFDeletar)
                    .addComponent(jFEditar)
                    .addComponent(jFAdicionar))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Funcionários", jPanel3);

        tReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Hotel", "Cliente", "CheckIn", "CheckOut", "Data Criado", "Data Pgto", "Cama Extra", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tReserva);
        if (tReserva.getColumnModel().getColumnCount() > 0) {
            tReserva.getColumnModel().getColumn(0).setMaxWidth(50);
            tReserva.getColumnModel().getColumn(8).setMaxWidth(50);
        }

        jRDeletar.setText("Deletar");
        jRDeletar.setEnabled(false);
        jRDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRDeletarActionPerformed(evt);
            }
        });

        jRAdicionar.setText("Adicionar");
        jRAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRAdicionarActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        jPanel9.setLocation(new java.awt.Point(1357, 324));
        jPanel9.setMinimumSize(new java.awt.Dimension(1357, 324));

        jLabel10.setText("Quarto:");

        jLabel11.setText("Hotel:");

        jLabel12.setText("Cliente:");

        jLabel13.setText("CheckIn:");

        jLabel14.setText("ChekcOut:");

        jLabel15.setText("Cama Extra:");

        jLabel16.setText("Estado:");

        jRCheckOut.setText(" ");

        jRCheckIn.setText(" ");

        jRCliente.setText(" ");

        jRHotel.setText(" ");

        JRQuarto.setText(" ");

        JRCamaExtra.setText(" ");

        JREstado.setText(" ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRCheckOut)
                    .addComponent(jRCheckIn)
                    .addComponent(jRCliente)
                    .addComponent(jRHotel)
                    .addComponent(JRQuarto)
                    .addComponent(JRCamaExtra)
                    .addComponent(JREstado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(JRQuarto))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jRHotel))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jRCliente))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jRCheckIn))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jRCheckOut))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(JRCamaExtra))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(JREstado))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(jRDeletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jRAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRDeletar))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reserva", jPanel4);

        tEstadia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Cliente", "Hotel", "Chekc In", "Check Out", "Reserva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tEstadia);
        if (tEstadia.getColumnModel().getColumnCount() > 0) {
            tEstadia.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jEDeletar.setText("Deletar");
        jEDeletar.setEnabled(false);
        jEDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEDeletarActionPerformed(evt);
            }
        });

        jEAdicionar.setText("Adicionar");
        jEAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEAdicionarActionPerformed(evt);
            }
        });

        jECheckOut.setText("Check Out");
        jECheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jECheckOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1337, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jECheckOut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jEAdicionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jEDeletar))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jEDeletar)
                    .addComponent(jEAdicionar)
                    .addComponent(jECheckOut))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Estadia", jPanel6);

        tServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Abreviação", "Descrição", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tServicos);
        if (tServicos.getColumnModel().getColumnCount() > 0) {
            tServicos.getColumnModel().getColumn(0).setMaxWidth(50);
            tServicos.getColumnModel().getColumn(1).setMaxWidth(150);
            tServicos.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        jSDeletar.setText("Deletar");
        jSDeletar.setEnabled(false);
        jSDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSDeletarActionPerformed(evt);
            }
        });

        jSAdicionar.setText("Adicionar");
        jSAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1337, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSAdicionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSDeletar))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSDeletar)
                    .addComponent(jSAdicionar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Serviços", jPanel7);

        tSPrestados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Estado", "Data", "Cortesia", "Serviço", "Funcionario", "Hotel"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tSPrestados);
        if (tSPrestados.getColumnModel().getColumnCount() > 0) {
            tSPrestados.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jSPDeletar.setText("Deletar");
        jSPDeletar.setEnabled(false);
        jSPDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSPDeletarActionPerformed(evt);
            }
        });

        jSPAdicionar.setText("Adicionar");
        jSPAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSPAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1337, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSPAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSPDeletar)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSPDeletar)
                    .addComponent(jSPAdicionar))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Serviços Prestados", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCAdicionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCAdicionarClienteActionPerformed
        // TODO add your handling code here:
        JanelaAdicionarCliente jc = new JanelaAdicionarCliente();
        jc.setLocationRelativeTo(null);
        jc.setVisible(true);
    }//GEN-LAST:event_jCAdicionarClienteActionPerformed

    private void jCAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCAtualizarActionPerformed
        // TODO add your handling code here:
        int codigo = Integer.valueOf(tClientes.getValueAt(tClientes.getSelectedRow(), 0).toString());

        Cliente cliente = Gerenciador
                .getInstancia()
                .getClientes()
                .get(codigo);

        JanelaAdicionarCliente jc = new JanelaAdicionarCliente(cliente);
        jc.setLocationRelativeTo(null);
        jc.setVisible(true);
    }//GEN-LAST:event_jCAtualizarActionPerformed

    private void jCDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCDeletarActionPerformed
        // TODO add your handling code here:
        StringBuilder valores = new StringBuilder();
        valores.append("DELETE FROM cliente WHERE cli_pk_codigo IN (");
        boolean primeiro = true;
        try {
            for (int i : tClientes.getSelectedRows()) {
                Cliente h = Gerenciador.getInstancia().getClientes().get(Integer.valueOf(tClientes.getValueAt(i, 0).toString()));

                if (primeiro) {
                    primeiro = false;
                } else {
                    valores.append(", ");
                }
                valores.append(h.getCodigo());
            }
            valores.append(")");

            System.out.println(valores.toString());
            ClienteBan2.getInstance().conexao.RequestChange(valores.toString());
            JOptionPane.showMessageDialog(this, "Valor(es) removido(s) com sucsso!", "Remoção executada", JOptionPane.INFORMATION_MESSAGE);
            Gerenciador.getInstancia().clear();
            this.setupTabela();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Problema ao remover valores!", "Remoção falhou", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jCDeletarActionPerformed

    private void jFAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFAdicionarActionPerformed
        // TODO add your handling code here:
        JanelaAdicionarFuncionario jf = new JanelaAdicionarFuncionario();
        jf.setLocationRelativeTo(this);
        jf.setVisible(true);
    }//GEN-LAST:event_jFAdicionarActionPerformed

    private void jFEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFEditarActionPerformed
        // TODO add your handling code here:
        int row = tFuncionario.getSelectedRow();
        int codigo = Integer.valueOf(tFuncionario.getValueAt(row, 0).toString());

        Funcionario fun = Gerenciador
                .getInstancia()
                .getFuncionarios()
                .get(codigo);

        JanelaAdicionarFuncionario jf = new JanelaAdicionarFuncionario(fun);
        jf.setLocationRelativeTo(this);
        jf.setVisible(true);
    }//GEN-LAST:event_jFEditarActionPerformed

    private void jRAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRAdicionarActionPerformed
        // TODO add your handling code here:
        JanelaCriaReserva jc = new JanelaCriaReserva();
        jc.setLocationRelativeTo(this);
        jc.setVisible(true);
    }//GEN-LAST:event_jRAdicionarActionPerformed

    private void jRDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRDeletarActionPerformed
        // TODO add your handling code here:
        StringBuilder valores = new StringBuilder();
        valores.append("DELETE FROM reserva WHERE res_pk_codigo IN (");
        boolean primeiro = true;
        try {
            for (int i : tReserva.getSelectedRows()) {
                Reserva h = Gerenciador.getInstancia().getReservas().get(Integer.valueOf(tReserva.getValueAt(i, 0).toString()));

                if (primeiro) {
                    primeiro = false;
                } else {
                    valores.append(", ");
                }
                valores.append(h.getCodigo());
            }
            valores.append(")");

            System.out.println(valores.toString());
            ClienteBan2.getInstance().conexao.RequestChange(valores.toString());
            JOptionPane.showMessageDialog(this, "Valor(es) removido(s) com sucesso!", "Remoção executada", JOptionPane.INFORMATION_MESSAGE);
            Gerenciador.getInstancia().clear();
            this.setupTabela();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Problema ao remover valores!", "Remoção falhou", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jRDeletarActionPerformed

    private void jFDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFDeletarActionPerformed
        // TODO add your handling code here:
        StringBuilder valores = new StringBuilder();
        valores.append("DELETE FROM funcionario WHERE fun_pk_codigo IN (");
        boolean primeiro = true;
        try {
            for (int i : tFuncionario.getSelectedRows()) {
                Funcionario h = Gerenciador.getInstancia().getFuncionarios().get(Integer.valueOf(tFuncionario.getValueAt(i, 0).toString()));

                if (primeiro) {
                    primeiro = false;
                } else {
                    valores.append(", ");
                }
                valores.append(h.getCodigo());
            }
            valores.append(")");

            System.out.println(valores.toString());
            ClienteBan2.getInstance().conexao.RequestChange(valores.toString());
            JOptionPane.showMessageDialog(this, "Valor(es) removido(s) com sucesso!", "Remoção executada", JOptionPane.INFORMATION_MESSAGE);
            Gerenciador.getInstancia().clear();
            this.setupTabela();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Problema ao remover valores!", "Remoção falhou", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jFDeletarActionPerformed

    private void jSDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSDeletarActionPerformed
        if (tServicos.getSelectedRowCount() == 0) {
            return;
        }
        StringBuilder valores = new StringBuilder();
        valores.append("DELETE FROM servico WHERE sev_pk_codigo IN (");
        boolean primeiro = true;
        try {
            for (int i : tServicos.getSelectedRows()) {
                Servico h = Gerenciador.getInstancia().getServicos().get(Integer.valueOf(tServicos.getValueAt(i, 0).toString()));

                if (primeiro) {
                    primeiro = false;
                } else {
                    valores.append(", ");
                }
                valores.append(h.getCodigo());
            }
            valores.append(")");

            System.out.println(valores.toString());
            ClienteBan2.getInstance().conexao.RequestChange(valores.toString());
            JOptionPane.showMessageDialog(this, "Valor(es) removido(s) com sucesso!", "Remoção executada", JOptionPane.INFORMATION_MESSAGE);
            Gerenciador.getInstancia().clear();
            this.setupTabela();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Problema ao remover valores!", "Remoção falhou", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jSDeletarActionPerformed

    private void jSPDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSPDeletarActionPerformed
        StringBuilder valores = new StringBuilder();
        valores.append("DELETE FROM servico_prestado WHERE svp_pk_codigo IN (");
        boolean primeiro = true;
        try {
            for (int i : tSPrestados.getSelectedRows()) {
                ServicoPrestado h = Gerenciador.getInstancia().getServicosPrestados().get(Integer.valueOf(tSPrestados.getValueAt(i, 0).toString()));

                if (primeiro) {
                    primeiro = false;
                } else {
                    valores.append(", ");
                }
                valores.append(h.getCodigo());
            }
            valores.append(")");

            System.out.println(valores.toString());
            ClienteBan2.getInstance().conexao.RequestChange(valores.toString());
            JOptionPane.showMessageDialog(this, "Valor(es) removido(s) com sucesso!", "Remoção executada", JOptionPane.INFORMATION_MESSAGE);
            Gerenciador.getInstancia().clear();
            this.setupTabela();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Problema ao remover valores!", "Remoção falhou", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jSPDeletarActionPerformed

    private void jCriaQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCriaQuartoActionPerformed
        // TODO add your handling code here:
        int row = tTabela1.getSelectedRow();
        int codigo = Integer.valueOf(tTabela1.getValueAt(row, 0).toString());

        Hotel hotel = Gerenciador
                .getInstancia()
                .getHoteis()
                .get(codigo);

        JanelaAdicionarQuarto jc = new JanelaAdicionarQuarto(hotel);
        jc.setLocationRelativeTo(this);
        jc.setVisible(true);
    }//GEN-LAST:event_jCriaQuartoActionPerformed

    private void bReload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReload1ActionPerformed
        // TODO add your handling code here:
        try {
            Gerenciador.getInstancia().clear();
            setupTabela();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_bReload1ActionPerformed

    private void bDeletar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeletar1ActionPerformed
        // TODO add your handling code here:
        StringBuilder valores = new StringBuilder();
        valores.append("DELETE FROM hotel WHERE hot_pk_codigo IN (");
        boolean primeiro = true;
        try {
            for (int i : tTabela1.getSelectedRows()) {
                Hotel h = Gerenciador.getInstancia().getHoteis().get(Integer.valueOf(tTabela1.getValueAt(i, 0).toString()));

                if (primeiro) {
                    primeiro = false;
                } else {
                    valores.append(", ");
                }
                valores.append(h.getCodigo());
            }
            valores.append(")");

            System.out.println(valores.toString());
            ClienteBan2.getInstance().conexao.RequestChange(valores.toString());
            JOptionPane.showMessageDialog(this, "Valor(es) removido(s) com sucsso!", "Remoção executada", JOptionPane.INFORMATION_MESSAGE);
            Gerenciador.getInstancia().clear();
            this.setupTabela();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Problema ao remover valores!", "Remoção falhou", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bDeletar1ActionPerformed

    private void bAtualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAtualizar1ActionPerformed
        // TODO add your handling code here:
        int row = tTabela1.getSelectedRow();
        int codigo = Integer.valueOf(tTabela1.getValueAt(row, 0).toString());

        Hotel hotel = Gerenciador
                .getInstancia()
                .getHoteis()
                .get(codigo);

        hotel.setCidade(hoteCidadeText.getText());
        hotel.setCnpj(hotelCNPJText.getText());
        hotel.setEndereco(hotelEnderecoText.getText());
        hotel.setEstrelas(Integer.parseInt(hoteEstrelasBox.getSelectedItem().toString()));
        hotel.setNome(hotelNomeText.getText());
        hotel.setUF(hoteUFText.getText());

        try {
            String query = hotel.updateQuery();
            ClienteBan2.getInstance().conexao.RequestChange(query);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Problema ao atualizar dados", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Valor alterado com sucsso!", "Alteração executada", JOptionPane.INFORMATION_MESSAGE);
        Gerenciador.getInstancia().clear();
        this.setupTabela();
        tTabela1.setRowSelectionInterval(row, row);
    }//GEN-LAST:event_bAtualizar1ActionPerformed

    private void bCriar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCriar1ActionPerformed
        // TODO add your handling code here:
        JanelaCriarHotel jc = new JanelaCriarHotel();
        jc.setLocationRelativeTo(null);
        jc.setVisible(true);
    }//GEN-LAST:event_bCriar1ActionPerformed

    private void jEAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEAdicionarActionPerformed
        // TODO add your handling code here:
        JanelaAdicionaEstadia je = new JanelaAdicionaEstadia();
        je.setLocationRelativeTo(this);
        je.setVisible(true);
    }//GEN-LAST:event_jEAdicionarActionPerformed

    private void jEDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEDeletarActionPerformed
        StringBuilder valores = new StringBuilder();
        valores.append("DELETE FROM estadia WHERE est_pk_codigo IN (");
        boolean primeiro = true;
        try {
            for (int i : tEstadia.getSelectedRows()) {
                Estadia h = Gerenciador.getInstancia().getEstadias().get(Integer.valueOf(tEstadia.getValueAt(i, 0).toString()));

                if (primeiro) {
                    primeiro = false;
                } else {
                    valores.append(", ");
                }
                valores.append(h.getCodigo());
            }
            valores.append(")");

            System.out.println(valores.toString());
            ClienteBan2.getInstance().conexao.RequestChange(valores.toString());
            JOptionPane.showMessageDialog(this, "Valor(es) removido(s) com sucsso!", "Remoção executada", JOptionPane.INFORMATION_MESSAGE);
            Gerenciador.getInstancia().clear();
            this.setupTabela();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Problema ao remover valores!", "Remoção falhou", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jEDeletarActionPerformed

    private void jSAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSAdicionarActionPerformed
        JanelaCriaServico js = new JanelaCriaServico();
        js.setLocationRelativeTo(this);
        js.setVisible(true);
    }//GEN-LAST:event_jSAdicionarActionPerformed

    private void jSPAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSPAdicionarActionPerformed
        // TODO add your handling code here:
        JanelaCriaServicoPrestado jsp = new JanelaCriaServicoPrestado();
        jsp.setLocationRelativeTo(this);
        jsp.setVisible(true);
    }//GEN-LAST:event_jSPAdicionarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (tTabela1.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor, escolha um hotel!", "Falha ao adicionar funcionario", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int row = tTabela1.getSelectedRow();
        int codigo = Integer.valueOf(tTabela1.getValueAt(row, 0).toString());

        Hotel hotel = Gerenciador
                .getInstancia()
                .getHoteis()
                .get(codigo);

        JanelaInserirFuncionarioHotel jsp = new JanelaInserirFuncionarioHotel(hotel);
        jsp.setLocationRelativeTo(this);
        jsp.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jECheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jECheckOutActionPerformed
        // TODO add your handling code here:
        int row = tEstadia.getSelectedRow();
        int codigo = Integer.valueOf(tEstadia.getValueAt(row, 0).toString());

        Estadia estadia = Gerenciador
                .getInstancia()
                .getEstadias()
                .get(codigo);

        SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd-yyyy");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        estadia.setCheckOut(strDate);

    }//GEN-LAST:event_jECheckOutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JRCamaExtra;
    private javax.swing.JLabel JREstado;
    private javax.swing.JLabel JRQuarto;
    private javax.swing.JButton bAtualizar1;
    private javax.swing.JButton bCriar1;
    private javax.swing.JButton bDeletar1;
    private javax.swing.JButton bReload1;
    private javax.swing.JTextField hoteCidadeText;
    private javax.swing.JComboBox<String> hoteEstrelasBox;
    private javax.swing.JTextField hoteUFText;
    private javax.swing.JTextField hotelCNPJText;
    private javax.swing.JTextField hotelCodigoText;
    private javax.swing.JTextField hotelEnderecoText;
    private javax.swing.JTextField hotelNomeText;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jCAdicionarCliente;
    private javax.swing.JButton jCAtualizar;
    private javax.swing.JButton jCDeletar;
    private javax.swing.JButton jCriaQuarto;
    private javax.swing.JButton jEAdicionar;
    private javax.swing.JButton jECheckOut;
    private javax.swing.JButton jEDeletar;
    private javax.swing.JButton jFAdicionar;
    private javax.swing.JButton jFDeletar;
    private javax.swing.JButton jFEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jRAdicionar;
    private javax.swing.JLabel jRCheckIn;
    private javax.swing.JLabel jRCheckOut;
    private javax.swing.JLabel jRCliente;
    private javax.swing.JButton jRDeletar;
    private javax.swing.JLabel jRHotel;
    private javax.swing.JButton jSAdicionar;
    private javax.swing.JButton jSDeletar;
    private javax.swing.JButton jSPAdicionar;
    private javax.swing.JButton jSPDeletar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tClientes;
    private javax.swing.JTable tEstadia;
    private javax.swing.JTable tFuncionario;
    private javax.swing.JTable tHFuncionarios;
    private javax.swing.JTable tHQuartos;
    private javax.swing.JTable tReserva;
    private javax.swing.JTable tSPrestados;
    private javax.swing.JTable tServicos;
    private javax.swing.JTable tTabela1;
    // End of variables declaration//GEN-END:variables
}
