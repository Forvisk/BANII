/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2;

import clienteban2.tabelas.Hotel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gustavo
 */
public class JanelaPrincipal extends javax.swing.JFrame {

    DefaultTableModel modeloTabela1;
    Tabela teste;
    int tabela1PKColumnIndex = 1;

    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal() {
        initComponents();

        modeloTabela1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        tTabela1.setModel(modeloTabela1);

        setupTabela();

    }

    public void setupTabela() {
        setTabela1();
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
            /*  Essa e a forma generia de fazer :)
            teste = new Tabela("hotel");
            teste.refreshResults();
            teste.getColunas().forEach((coluna) -> {
            System.out.println(coluna.getNome());
            modeloTabela1.addColumn(coluna.getNome().toUpperCase());
            });
            teste.getConteudo().getDados().stream().map((LinkedHashMap<String, String> elemento) -> {
            ArrayList<String> row = new ArrayList<>();
            elemento.entrySet().forEach((linha) -> {
            row.add(linha.getValue());
            });
            return row;
            }).forEachOrdered((row) -> {
            modeloTabela1.addRow(row.toArray());
            });
             */

        } catch (Exception ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        tTabela1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        ListSelectionModel selectionModel = tTabela1.getSelectionModel();

        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            try {
                int selectedRowNum = tTabela1.getSelectedRowCount();
                System.out.println(selectedRowNum);
                if (selectedRowNum == 1) {

                    int codigo = Integer.valueOf(tTabela1.getValueAt(tTabela1.getSelectedRow(), 0).toString());

                    Hotel hotel = Gerenciador
                            .getInstancia()
                            .getHoteis()
                            .get(codigo);

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

                } else if (selectedRowNum > 1) {
                    bDeletar1.setEnabled(true);
                    bDeletar1.setToolTipText("Clique para deletar essas " + selectedRowNum + " linhas!");

                    bAtualizar1.setEnabled(false);
                    bAtualizar1.setToolTipText("Não é possivel salvar mais de um por vez!");

                    // 0 selected
                } else {
                    bAtualizar1.setEnabled(false);
                    bAtualizar1.setToolTipText("Selecione uma linha!");

                    bDeletar1.setEnabled(false);
                    bDeletar1.setToolTipText("Selecione uma linha!");
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

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
        bAtualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtualizar1ActionPerformed(evt);
            }
        });

        bDeletar1.setText("Deletar");
        bDeletar1.setToolTipText("Clique para deletar!");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(hotelCodigoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(21, 21, 21)
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
                    .addComponent(jLabel6))
                .addContainerGap(55, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 882, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 882, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 882, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab4", jPanel4);

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
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCriar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCriar1ActionPerformed
        // TODO add your handling code here:
        JanelaCriar jc = new JanelaCriar(this, teste);
        jc.setLocationRelativeTo(null);
        jc.setVisible(true);
    }//GEN-LAST:event_bCriar1ActionPerformed

    private void bReload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReload1ActionPerformed
        // TODO add your handling code here:
        try {
            setupTabela();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_bReload1ActionPerformed

    private void bDeletar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeletar1ActionPerformed
        // TODO add your handling code here:
        StringBuilder valores = new StringBuilder();
        valores.append("DELETE FROM ");
        valores.append(teste.getName());
        valores.append(" WHERE ");
        valores.append(teste.getPrimaryKey());
        valores.append(" IN (");
        boolean primeiro = true;
        for (int i : tTabela1.getSelectedRows()) {
            if (primeiro) {
                primeiro = false;
            } else {
                valores.append(", ");
            }
            valores.append(tTabela1.getValueAt(i, tabela1PKColumnIndex));
        }
        valores.append(")");
        try {
            ClienteBan2.getInstance().conexao.RequestChange(valores.toString());
            JOptionPane.showMessageDialog(this, "Valor(es) removido(s) com sucsso!", "Remoção executada", JOptionPane.INFORMATION_MESSAGE);
            this.setupTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problema ao remover valores!", "Remoção falhou", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bDeletar1ActionPerformed

    private void bAtualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAtualizar1ActionPerformed
        // TODO add your handling code here:
        int codigo = Integer.valueOf(tTabela1.getValueAt(tTabela1.getSelectedRow(), 0).toString());

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

        hotel.atualizaBD();
        ClienteBan2.getInstance().conexao.RequestChange(valores.toString());

        JOptionPane.showMessageDialog(this, "Valor alterado com sucsso!", "Alteração executada", JOptionPane.INFORMATION_MESSAGE);
        this.setupTabela();
        tTabela1.setRowSelectionInterval(rowSelecionada, rowSelecionada);

    }//GEN-LAST:event_bAtualizar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tTabela1;
    // End of variables declaration//GEN-END:variables
}
