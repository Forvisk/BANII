/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2;

import clienteban2.tabelas.Cliente;
import clienteban2.tabelas.Funcionario;
import clienteban2.tabelas.Hotel;
import clienteban2.tabelas.Quarto;
import java.util.ArrayList;
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
    Tabela teste;
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
        tTabela1.setModel(modeloTabela1);

        setupTabela();

    }

    public void setupTabela() {
        Gerenciador.getInstancia().refresh();
        setTabela1();
        setTabelaClientes();
        setTabelaFuncionarios();
        setTabelaReserva();
    }

    private void setTabelaReserva() {
        modeloTabelaReserva.setRowCount(0);
        try {
            Gerenciador.getInstancia().getFuncionarios().values().stream().map((cliente) -> {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(cliente.getCodigo()));
                row.add(cliente.getNome());
                row.add(cliente.getTelefone());
                row.add(cliente.getCargo());
                row.add("" + cliente.getEstado());
                row.add(cliente.getHotel().getNome());
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
                    Cliente cli = Gerenciador.getInstancia().getClientes().get(codigo);

                    jRDeletar.setEnabled(true);
                    jRDeletar.setToolTipText("Clique para deletar!");

                } else if (selectedRowNum > 1) {
                    jRDeletar.setEnabled(true);
                    jRDeletar.setToolTipText("Clique para deletar essas " + selectedRowNum + " linhas!");

                    // 0 selected
                } else {
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
                row.add(cliente.getHotel().getNome());
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
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bReload1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 821, Short.MAX_VALUE)
                        .addComponent(bCriar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bAtualizar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bDeletar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
        jCDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCDeletarActionPerformed(evt);
            }
        });

        jCAtualizar.setText("Editar");
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
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCAtualizar)
                    .addComponent(jCDeletar)
                    .addComponent(jCAdicionarCliente))
                .addContainerGap())
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

        jFEditar.setText("Editar");

        jFAdicionar.setText("Adicionar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jFAdicionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFDeletar))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tReserva);

        jRDeletar.setText("Deletar");

        jRAdicionar.setText("Adicionar");

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));

        jLabel10.setText("Quarto:");

        jLabel11.setText("Hotel:");

        jLabel12.setText("Cliente:");

        jLabel13.setText("CheckIn:");

        jLabel14.setText("ChekcOut:");

        jLabel15.setText("Cama Extra:");

        jLabel16.setText("Estado:");

        jRCheckOut.setText("jLabel17");

        jRCheckIn.setText("jLabel17");

        jRCliente.setText("jLabel17");

        jRHotel.setText("jLabel17");

        JRQuarto.setText("jLabel17");

        JRCamaExtra.setText("jLabel17");

        JREstado.setText("jLabel17");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(31, 31, 31)
                                .addComponent(JRQuarto))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRHotel)
                                    .addComponent(jRCliente)
                                    .addComponent(jRCheckIn))))
                        .addGap(443, 443, 443)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JREstado)
                            .addComponent(JRCamaExtra)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRCheckOut)))
                .addContainerGap(482, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15)
                    .addComponent(JRQuarto)
                    .addComponent(JRCamaExtra))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(JREstado))
                    .addGroup(jPanel9Layout.createSequentialGroup()
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
                            .addComponent(jRCheckIn))))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jRCheckOut))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(6, 6, 6)
                        .addComponent(jRAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRDeletar))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reserva", jPanel4);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1342, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Estadia", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1342, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Serviços", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1342, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Serviços Prestados", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        this.setupTabela();
        tTabela1.setRowSelectionInterval(row, row);
    }//GEN-LAST:event_bAtualizar1ActionPerformed

    private void bCriar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCriar1ActionPerformed
        // TODO add your handling code here:
        JanelaCriarHotel jc = new JanelaCriarHotel();
        jc.setLocationRelativeTo(null);
        jc.setVisible(true);
    }//GEN-LAST:event_bCriar1ActionPerformed

    private void jCAdicionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCAdicionarClienteActionPerformed
        // TODO add your handling code here:
        JanelaAdicionarCliente jc = new JanelaAdicionarCliente();
        jc.setLocationRelativeTo(null);
        jc.setVisible(true);
    }//GEN-LAST:event_jCAdicionarClienteActionPerformed

    private void jCAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCAtualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCAtualizarActionPerformed

    private void jCDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCDeletarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCDeletarActionPerformed

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tClientes;
    private javax.swing.JTable tFuncionario;
    private javax.swing.JTable tHFuncionarios;
    private javax.swing.JTable tHQuartos;
    private javax.swing.JTable tReserva;
    private javax.swing.JTable tTabela1;
    // End of variables declaration//GEN-END:variables
}
